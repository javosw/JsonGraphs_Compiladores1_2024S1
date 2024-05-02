/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package josq.lenguajes.automatas;

import java_cup.runtime.Symbol;
import java_cup.runtime.DefaultSymbolFactory;
import java_cup.runtime.Scanner;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 *
 * @author JavierOswaldo
 */
public class Automata
{
    private static Object getDashb(Reader myReader) throws Exception
    {
        // procesadores de lenguaje
        DefaultSymbolFactory myFactory = new DefaultSymbolFactory();
        Scanner lexer = new LexerDashb(myReader, myFactory);
        ParserDashb parser = new ParserDashb(lexer, myFactory);

        // resultado de procesamiento
        Symbol mySymbol = parser.parse();

        //return (MyType) (mySymbol.value);
        return mySymbol.value;
    }
    public static Object getDashbDesdeArchivo(String ruta) throws Exception
    {
        FileInputStream myStream = new FileInputStream(ruta);
        Reader myReader = new InputStreamReader(myStream);

        return getDashb(myReader);
    }
    public static Object getDashbDesdeString(String texto) throws Exception
    {
        Reader myReader = new StringReader(texto);

        return getDashb(myReader);
    }
}
