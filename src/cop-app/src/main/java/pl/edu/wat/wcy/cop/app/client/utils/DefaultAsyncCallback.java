/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils;

import com.google.gwt.user.client.rpc.AsyncCallback;
// Represents default async callback.


public abstract class DefaultAsyncCallback<T> implements AsyncCallback<T> {

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.
     * Throwable)
     */
    @Override
    public void onFailure(Throwable caught) {
    }

}
