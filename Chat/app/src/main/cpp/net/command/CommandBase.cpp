//
// Created by machenfei on 2017/4/1.
//

#include "NetController.h"
#include "CommandBase.h"

void CommandBase::send() {
    NetController::shared()->send(this);
}

bool CommandBase::handleReceive() {
    return false;
}
