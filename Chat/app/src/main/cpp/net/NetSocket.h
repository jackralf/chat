//
// Created by machenfei on 2017/3/31.
//

#ifndef CHAT_NETSOCKET_H
#define CHAT_NETSOCKET_H


#include <string>
#include "common.h"

class NetSocket {

public:
    NetSocket(std::string ip, int port);
    ~NetSocket();

    inline bool isConnected() { return m_bConnected;}

    void init();
    bool initSocket();
    bool initThread();

    bool connectSocket();
    void closeSocket();

private:
    std::string m_ip;
    int m_port;
    int m_hSocket;
    bool m_bConnected;

    pthread_t m_pNetSendThread;
    pthread_t m_pNetRecvThread;
};


#endif //CHAT_NETSOCKET_H
