// ICallService.aidl
package com.livermor.sharedmem;

import com.livermor.sharedmem.ICallback;

interface ICallService {

    String getMessage(String name);
    
    int getResult(int val1, int val2);

    void onMessageBuilt(in ICallback callback, String msg);

    void sort(inout int[] arr);
}
