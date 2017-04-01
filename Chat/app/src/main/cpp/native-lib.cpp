#include <jni.h>
#include <string>
#include "NetSocket.h"


extern "C" {

JNIEXPORT void JNICALL
Java_com_machenfei_chat_net_NetSocketManager_initNativeSocket(JNIEnv *env, jobject instance) {

    auto cmd = new CommandBase(1);
    for (int i = 0; i < 10; ++i) {
        cmd->send();
    }
}

}

