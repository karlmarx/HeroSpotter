package testJython;

import org.python.util.PythonInterpreter;

public class testJython {
    public static void main(String[] args) {
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec("print('Hello Python World!')");
        }
    }
}
