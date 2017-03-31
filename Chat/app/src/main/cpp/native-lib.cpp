#include <jni.h>
#include <string>
#include "NetSocket.h"


extern "C" {

JNIEXPORT void JNICALL
Java_com_machenfei_chat_net_NetSocketManager_initNativeSocket(JNIEnv *env, jobject instance) {

    NetSocket *net = new NetSocket("127.0.0.1", 80);
}

}

