package com.tw.async.future;

public interface DependentFuture<T> extends Future<T>, DependentCancellable {
}
