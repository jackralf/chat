//
// Created by machenfei on 2017/4/1.
//

#include "NetController.h"

NetController* NetController::m_pInstance = NULL;
NetController *NetController::shared() {
    if (m_pInstance == NULL) {
        m_pInstance = new NetController();
    }
    return m_pInstance;
}

NetController::NetController() {
    m_pNetSocket = new NetSocket("192.168.1.102", "5000");
}

void NetController::send(CommandBase *command) {
    m_pNetSocket->writeSendBuffer(command);
}
