//
// Created by machenfei on 2017/4/1.
//

#include "NetBuffer.h"
#include <arpa/inet.h>

NetBuffer::NetBuffer():m_iDataType(0),m_iBufferLength(0) {
    clearBuffer();
}

NetBuffer::~NetBuffer() {

}

int NetBuffer::writeBuffer(CommandBase *command) {
    int type = command->getType();
    std::string data = command->getData();
    int dataLen = command->getDataLength();
    if (dataLen > MAX_BUFFER_LENGTH - TYPE_INFO_SIZE - LENGTH_INFO_SIZE) {
        LOGD("WriteBuffer-over max buff length, data length = %d", dataLen);
        return 0;
    }

    m_iDataType = type;
    int tmp = htons(type);
    memcpy(m_pBuffer, &tmp, TYPE_INFO_SIZE);
    tmp = htonl(dataLen);
    memcpy(m_pBuffer + TYPE_INFO_SIZE, &tmp, LENGTH_INFO_SIZE);
    memcpy(m_pBuffer + TYPE_INFO_SIZE + LENGTH_INFO_SIZE, data.c_str(), dataLen);
    m_iBufferLength = dataLen + TYPE_INFO_SIZE + LENGTH_INFO_SIZE;
    LOGD("write buffer data: %s", data);

    return dataLen;
}

void NetBuffer::clearBuffer() {
    memset(m_pBuffer, 0, MAX_BUFFER_LENGTH);
    m_iBufferLength = 0;
}




