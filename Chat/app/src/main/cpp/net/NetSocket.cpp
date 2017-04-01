//
// Created by machenfei on 2017/3/31.
//

#include "NetSocket.h"
#include <sys/socket.h>
#include <sys/errno.h>
#include <arpa/inet.h>
#include <netinet/tcp.h>
#include <unistd.h>
#include <netdb.h>
#include <stdlib.h>
#include "NetBuffer.h"

static void* ThreadSendProtocol(void* pData) {
    LOGD("send thread start");
    NetSocket *pSocket = static_cast<NetSocket*>(pData);
    while (true) {
        if (!pSocket->isConnected()) {
            continue;
        }
        NetBuffer* pBuffer = pSocket->getFirstBuffer();
        if (!pBuffer) {
            continue;
        }
        ssize_t ret = send(pSocket->getSocketHandle(), pBuffer->getBuffer(), (ssize_t)pBuffer->getBufferLength(), 0);
        if (ret <= 0) {
            LOGD("socket send error");
        }
        LOGD("send data by socket: send type = %d , len = %d", pBuffer->getDataType(), (int)ret);
        pSocket->popFirstBuffer();
    }
    pSocket->closeSocket();
    return (void*)0;
}

static void* ThreadRecvProtocol(void* pData) {
    LOGD("recv thread start");
    NetSocket *pSocket = static_cast<NetSocket*>(pData);
    while (true) {
        if (!pSocket->isConnected()) {
            continue;
        }


    }

    return (void*)0;
}

NetSocket::NetSocket(std::string ip, std::string port):m_ip(ip),m_port(port) {
    m_hSocket = -1;
    m_bConnected = false;
    m_bSendThreadInProc = false;
    m_bRecvThreadInProc = false;
    pthread_mutex_init(&m_threadMutexSend, NULL);
    pthread_mutex_init(&m_threadMutexRecv, NULL);

    init();
}

NetSocket::~NetSocket() {

}

void NetSocket::init() {
    m_bConnected = initSocket();
    if (m_bConnected) {
        initThread();
    }
}

bool NetSocket::initSocket() {
    LOGD("init socket");
    m_hSocket = socket(AF_INET, SOCK_STREAM, 0);
    if (m_hSocket == -1) {
        LOGD("socket init error! errno=%d", errno);
    }
    int flag = 1;
    setsockopt(m_hSocket, IPPROTO_TCP, TCP_NODELAY, (char*)&flag, sizeof(int));

    bool ret = connectSocket();
    return ret;
}

bool NetSocket::initThread() {
    LOGD("init thread");
    if (!m_bSendThreadInProc) {
        pthread_create(&m_pNetSendThread, NULL, ThreadSendProtocol, this);
        m_bSendThreadInProc = true;
    }
    if (!m_bRecvThreadInProc) {
        pthread_create(&m_pNetRecvThread, NULL, ThreadRecvProtocol, this);
        m_bRecvThreadInProc = true;
    }

    return true;
}

bool NetSocket::connectSocket() {
    LOGD("connect socket");
    auto info = gethostbyname(m_ip.c_str());
    if (!info) return false;

    char str[32] = "";
    inet_ntop(info->h_addrtype, *info->h_addr_list, str, sizeof(str));
    LOGD("address:%s", str);

    sockaddr_in socketAddr;
    memset(&socketAddr, 0, sizeof(socketAddr));
    socketAddr.sin_family = AF_INET;
    socketAddr.sin_port = htons(atoi(m_port.c_str()));
    socketAddr.sin_addr.s_addr = inet_addr(str);
    memset(&(socketAddr.sin_zero), 0, sizeof(socketAddr.sin_zero));

    int error = connect(m_hSocket, (sockaddr *)&socketAddr, sizeof(socketAddr));
    if (error == -1) {
        LOGD("socket connect error :%d", errno);
        return false;
    }

    return true;
}

void NetSocket::closeSocket() {
    if (m_hSocket != -1) {
        LOGD("close socket");
        shutdown(m_hSocket, SHUT_RDWR);
        close(m_hSocket);
        m_hSocket = -1;
        m_bConnected = false;
    }
}

int NetSocket::writeSendBuffer(CommandBase *command) {
    NetBuffer* pBuffer = new NetBuffer();
    int writeLen = pBuffer->writeBuffer(command);

    if (writeLen > 0) {
        pthread_mutex_lock(&m_threadMutexSend);
        m_vNetSendBufferList.push_back(pBuffer);
        pthread_mutex_unlock(&m_threadMutexSend);
    } else {
        delete pBuffer;
    }

    return writeLen;
}

NetBuffer *NetSocket::getFirstBuffer() {
    pthread_mutex_lock(&m_threadMutexSend);
    NetBuffer* pBuffer = m_vNetSendBufferList.size() > 0 ? m_vNetSendBufferList[0] : NULL;
    pthread_mutex_unlock(&m_threadMutexSend);
    return pBuffer;
}

void NetSocket::popFirstBuffer() {
    pthread_mutex_lock(&m_threadMutexSend);
    delete (*m_vNetSendBufferList.begin());
    m_vNetSendBufferList.erase(m_vNetSendBufferList.begin());
    pthread_mutex_unlock(&m_threadMutexSend);
}


