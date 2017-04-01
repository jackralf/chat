//
// Created by machenfei on 2017/4/1.
//

#ifndef CHAT_NETCONTROLLER_H
#define CHAT_NETCONTROLLER_H


#include "NetSocket.h"

class NetController {
public:
    NetController();
    ~NetController();

    static NetController* shared();
    void send(CommandBase* command);
private:
    static NetController* m_pInstance;
    NetSocket* m_pNetSocket;
};


#endif //CHAT_NETCONTROLLER_H
