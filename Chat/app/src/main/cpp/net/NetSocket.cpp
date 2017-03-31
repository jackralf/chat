//
// Created by machenfei on 2017/3/31.
//

#include "NetSocket.h"
#include <sys/socket.h>
#include <sys/errno.h>
#include <arpa/inet.h>
#include <netinet/tcp.h>
#include <unistd.h>

static void* ThreadSendProtocol(void* pData) {
    NetSocket *pSocket = static_cast<NetSocket*>(pData);
    while (true) {
        if (!pSocket->isConnected()) {
            continue;
        }


    }
    pSocket->closeSocket();
    return (void*)0;
}

NetSocket::NetSocket(std::string ip, int port):m_ip(ip),m_port(port) {
    m_hSocket = -1;
    m_bConnected = false;
    init();
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

    if (!connectSocket()) {
        return false;
    }

    return true;
}

bool NetSocket::initThread() {
    LOGD("init thread");
    pthread_create(&m_pNetSendThread, NULL, ThreadSendProtocol, this);

    return true;
}

bool NetSocket::connectSocket() {
    LOGD("connect socket");

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