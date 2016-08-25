package org.highj.do_;

import org.derive4j.hkt.Leibniz;
import org.derive4j.hkt.__;
import org.highj.data.tuple.T0;
import org.highj.data.tuple.T2;
import org.highj.function.F1;
import org.highj.function.F2;
import org.highj.function.F3;
import org.highj.typeclass1.monad.Monad;
import org.highj.typeclass1.monad.MonadRec;

public class Do_<M,A> {
    private final DoIndexed<M, T0, A> _impl;

    private Do_(DoIndexed<M, T0, A> impl) {
        this._impl = impl;
    }

    private static <M,A> Do_<M,A> create(DoIndexed<M, T0, A> impl) {
        return new Do_<>(impl);
    }

    private DoIndexed<M,T0,A> impl() {
        return _impl;
    }

    public <S,Y> __<M,Y> runWithResult(Leibniz<A,T2<S,Y>> leibniz, MonadRec<M> mMonadRec) {
        return mMonadRec.map(
            (A a) -> leibniz.coerce(a)._2(),
            DoIndexed.run(mMonadRec, impl())
        );
    }

    public __<M,T0> runNoResult(MonadRec<M> mMonadRec) {
        return mMonadRec.rightSeq(
            DoIndexed.run(mMonadRec, impl()),
            mMonadRec.pure(T0.of())
        );
    }

    public static <M> Do_<M,T0> do_() {
        return create(DoIndexed.<M>do_());
    }

    public <B> Do_<M,A> __(__<M,B> m) {
        return create(impl().__(m));
    }

    public <B> Do_<M, T2<A,B>> pushM(__<M,B> m) {
        return create(impl().pushM(m));
    }

    public <B> Do_<M, B> mapAll(F1<A,B> f) {
        return create(impl().map(f));
    }

    public <B> Do_<M,B> bindAll(F1<A,__<M,B>> f) {
        return create(impl().bind((Monad<M> mMonad, A a) -> f.apply(a)));
    }

    public <S,X,Y> Do_<M,T2<S,Y>> map1(Leibniz<A,T2<S,X>> leibniz, F1<X,Y> f) {
        return create(impl().map((A a) -> {
            T2<S,X> x = leibniz.coerce(a);
            return T2.of(x._1(), f.apply(x._2()));
        }));
    }

    public <S,X1,X2,Y> Do_<M,T2<S,Y>> maT2(Leibniz<A,T2<T2<S,X1>,X2>> leibniz, F2<X1,X2,Y> f) {
        return create(impl().map((A a) -> {
            T2<T2<S,X1>,X2> x = leibniz.coerce(a);
            return T2.of(x._1()._1(), f.apply(x._1()._2(), x._2()));
        }));
    }

    public <S,X1,X2,X3,Y> Do_<M,T2<S,Y>> map3(Leibniz<A,T2<T2<T2<S,X1>,X2>,X3>> leibniz, F3<X1,X2,X3,Y> f) {
        return create(impl().map((A a) -> {
            T2<T2<T2<S,X1>,X2>,X3> x = leibniz.coerce(a);
            return T2.of(x._1()._1()._1(), f.apply(x._1()._1()._2(), x._1()._2(), x._2()));
        }));
    }

    public <S,X,Y> Do_<M,S> bind1(Leibniz<A,T2<S,X>> leibniz, F1<X,__<M,Y>> f) {
        return create(impl().bind((Monad<M> mMonad, A a) -> {
            T2<S,X> x = leibniz.coerce(a);
            return mMonad.rightSeq(f.apply(x._2()), mMonad.pure(x._1()));
        }));
    }

    public <S,X1,X2,Y> Do_<M,S> bind2(Leibniz<A,T2<T2<S,X1>,X2>> leibniz, F2<X1,X2,__<M,Y>> f) {
        return create(impl().bind((Monad<M> mMonad, A a) -> {
            T2<T2<S,X1>,X2> x = leibniz.coerce(a);
            return mMonad.rightSeq(f.apply(x._1()._2(), x._2()), mMonad.pure(x._1()._1()));
        }));
    }

    public <S,X1,X2,X3,Y> Do_<M,S> bind3(Leibniz<A,T2<T2<T2<S,X1>,X2>,X3>> leibniz, F3<X1,X2,X3,__<M,Y>> f) {
        return create(impl().bind((Monad<M> mMonad, A a) -> {
            T2<T2<T2<S,X1>,X2>,X3> x = leibniz.coerce(a);
            return mMonad.rightSeq(f.apply(x._1()._1()._2(), x._1()._2(), x._2()), mMonad.pure(x._1()._1()._1()));
        }));
    }

    public <S,X,Y> Do_<M,T2<S,Y>> pushBind1(Leibniz<A,T2<S,X>> leibniz, F1<X,__<M,Y>> f) {
        return create(impl().bind((Monad<M> mMonad, A a) -> {
            T2<S,X> x = leibniz.coerce(a);
            return mMonad.map((Y y) -> T2.of(x._1(), y), f.apply(x._2()));
        }));
    }

    public <S,X1,X2,Y> Do_<M,T2<S,Y>> pushBind2(Leibniz<A,T2<T2<S,X1>,X2>> leibniz, F2<X1,X2,__<M,Y>> f) {
        return create(impl().bind((Monad<M> mMonad, A a) -> {
            T2<T2<S,X1>,X2> x = leibniz.coerce(a);
            return mMonad.map((Y y) -> T2.of(x._1()._1(), y), f.apply(x._1()._2(), x._2()));
        }));
    }

    public <S,X1,X2,X3,Y> Do_<M,T2<S,Y>> pushBind3(Leibniz<A,T2<T2<T2<S,X1>,X2>,X3>> leibniz, F3<X1,X2,X3,__<M,Y>> f) {
        return create(impl().bind((Monad<M> mMonad, A a) -> {
            T2<T2<T2<S,X1>,X2>,X3> x = leibniz.coerce(a);
            return mMonad.map((Y y) -> T2.of(x._1()._1()._1(), y), f.apply(x._1()._1()._2(), x._1()._2(), x._2()));
        }));
    }
}
