package org.highj.do_;

import org.derive4j.hkt.Leibniz;
import org.highj.data.stateful.SafeIO;
import org.highj.data.tuple.T0;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static SafeIO<T0> writeLineIO(String msg) {
        return () -> {
            System.out.println(msg);
            return T0.of();
        };
    }

    public static SafeIO<String> readLineIO(Scanner s) {
        return s::nextLine;
    }

    public static void main( String[] args )
    {
        try (Scanner s = new Scanner(System.in)) {
            SafeIO.narrow(
                Do_.<SafeIO.µ>do_()
                    .__(writeLineIO("What is your name?"))
                    .pushM(readLineIO(s))
                    .bind1(
                        Leibniz.refl(),
                        (String name) ->
                            writeLineIO("Hello " + name + "!")
                    )
                    .runNoResult(SafeIO.monadRec)
            ).run();
        }
    }
}
