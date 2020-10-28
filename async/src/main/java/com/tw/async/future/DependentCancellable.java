package com.tw.async.future;

public interface DependentCancellable extends Cancellable {
    boolean setParent(Cancellable parent);
}
