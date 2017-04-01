//
// Created by machenfei on 2017/3/31.
//

#ifndef CHAT_NETSOCKET_H
#define CHAT_NETSOCKET_H


#include <string>
#include <vector>
#include <net/command/CommandBase.h>
#include "common.h"

class NetBuffer;
typedef std::vector<NetBuffer*> NETBUFFER_LIST;
class NetSocket {

public:
    NetSocket(std::string ip, std::string port);
    ~NetSocket();

    inline bool isConnected() { return m_bConnected;}
    inline int getSocketHandle() { return m_hSocket; }

    void init();
    bool initSocket();
    bool initThread();

    bool connectSocket();
    void closeSocket();

    int writeSendBuffer(CommandBase* command);
    NetBuffer* getFirstBuffer();
    void popFirstBuffer();
private:
    std::string m_ip;
    std::string m_port;
    int m_hSocket;
    bool m_bConnected;

    pthread_t m_pNetSendThread;
    pthread_t m_pNetRecvThread;

    bool m_bSendThreadInProc;
    bool m_bRecvThreadInProc;

    NETBUFFER_LIST m_vNetSendBufferList;
    NETBUFFER_LIST m_vNetRecvBufferList;

    pthread_mutex_t m_threadMutexSend;
    pthread_mutex_t m_threadMutexRecv;
};


#endif //CHAT_NETSOCKET_H
