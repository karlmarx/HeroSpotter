package jython;

import org.python.util.PythonInterpreter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec("print('Hello Python World!')");
        }
    }
}
