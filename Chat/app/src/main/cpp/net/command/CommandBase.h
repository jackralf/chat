//
// Created by machenfei on 2017/4/1.
//

#ifndef CHAT_COMMANDBASE_H
#define CHAT_COMMANDBASE_H


#include <string>

class CommandBase {
public:
    CommandBase(int type):m_iType(type) {
        //TODO test
        m_sData = "hello world";
        m_iDataLength = m_sData.size();
    }

    inline int getType() { return m_iType; }
    inline std::string getData() { return m_sData; }
    inline int getDataLength() { return m_iDataLength; };
    void send();
    virtual bool handleReceive();

private:
    int m_iType;
    std::string m_sData;
    int m_iDataLength;
};


#endif //CHAT_COMMANDBASE_H
