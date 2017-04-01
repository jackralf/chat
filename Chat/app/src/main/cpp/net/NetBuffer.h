//
// Created by machenfei on 2017/4/1.
//

#ifndef CHAT_NETBUFFER_H
#define CHAT_NETBUFFER_H

#include "CommandBase.h"
#include "common.h"

#define MAX_BUFFER_LENGTH 1024 * 8
#define TYPE_INFO_SIZE 2
#define LENGTH_INFO_SIZE 4

class NetBuffer {
public:
    NetBuffer();
    ~NetBuffer();

    inline int getBufferLength() { return m_iBufferLength; }
    inline char* getBuffer() { return m_pBuffer; }
    inline int getDataType() { return m_iDataType; }

    int writeBuffer(CommandBase *command);
    void clearBuffer();
private:
    char m_pBuffer[MAX_BUFFER_LENGTH];
    int m_iBufferLength;

    int m_iDataType;
};


#endif //CHAT_NETBUFFER_H
