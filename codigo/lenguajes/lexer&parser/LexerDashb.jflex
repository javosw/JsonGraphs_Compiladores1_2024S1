// meta characters:  |  (  )  {  }  [  ]  < >  \  .  *  +  ?  ^  $  / . " ~ !

// codigo antes de la clase lexer
package josq.cms.lenguajes.automatas;

import java.io.Reader;

//import java_cup.runtime.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.DefaultSymbolFactory;


%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%

// configuracion de jflex
//%debug
%16bit
// configuracion de la clase lexer
%class LexerDashb
%public
//%apiprivate 
%cupsym ParserDashbSym
%cup
%line
%column
%char

// codigo dentro de la clase lexer
%{
    public LexerDashb(Reader myReader, DefaultSymbolFactory myFactory) { this(myReader); this.myFactory = myFactory; }

    private DefaultSymbolFactory myFactory = null;

    private Symbol symbol(String name, int sym) {
        int izq = (int)yychar+1;
        int der = (int)yychar+yylength();
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der);
        return mySymbol;
    }
    private Symbol symbol(String name, int sym, Object val) {
        int izq = (int)yychar+1;
        int der = (int)yychar+yylength();
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der, val);
        return mySymbol;
    }

    // para errores lexicos
    public Punto getPunto(){ return new Punto(yycolumn, yyline, yylength(), (int)yychar+1); };
    StringBuilder log(String text) 
    {
        EjecutarAcciones.logSintaxis.append(text); 
        return EjecutarAcciones.logSintaxis; 
    }
    
    // para manejo de contextos lexicos
    private boolean accioncEsInpar = false;
    private boolean parametroEsInpar = false;
    private boolean atributoEsInpar = false;

    private void setContextoDe(int sym)
    {
        if(sym == ParserAccionesSym.ACCI)
        { 
            if (accioncEsInpar) { yybegin(YYINITIAL); }
            else { yybegin(MI_ACCION); }
            accioncEsInpar = !accioncEsInpar;
        }
        else if(sym == ParserAccionesSym.PARAM)
        { 
            if (parametroEsInpar) { yybegin(YYINITIAL); }
            else { yybegin(MI_PARAMETRO); }
            parametroEsInpar = !parametroEsInpar;
        }
        else if(sym == ParserAccionesSym.ATRIB)
        { 
            if (atributoEsInpar) { yybegin(YYINITIAL); }
            else { yybegin(MI_ATRIBUTO); }
            atributoEsInpar = !atributoEsInpar;
        }
    }

    /*
    ComplexSymbolFactory myFactory = null;

    public LexerAcciones(Reader in, ComplexSymbolFactory sf) { this(in); myFactory = sf; }

    private Symbol symbol(String name, int sym) {
        Location izq = new Location(yyline+1, yycolumn+1, (int)yychar);
        Location der = new Location(yyline+1, yycolumn+yylength(), (int)yychar+yylength());
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der);
        return mySymbol;
    }
    private Symbol symbol(String name, int sym, Object val) {
        Location izq = new Location(yyline+1, yycolumn+1, (int)yychar);
        Location der = new Location(yyline+1, yycolumn+yylength(), (int)yychar+yylength());
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der, val);
        return mySymbol;
    }*/
%}

// estados lexicos
//%xstate CONTEXTO_1
%state MI_ACCION, MI_PARAMETRO, MI_ATRIBUTO
%state MI_ID, MI_ID_USER
%state MI_NUMERO
%state MI_TEXTO, MI_TITULO, MI_ETIQUETA, MIS_ETIQUETAS
%state MI_COLOR, MI_FECHA, MI_URL
%state UI_WEB, MI_ALIGN

// macros para regex

// invisible vertical
_v   =  \r|\n|\r\n
// invisible horizontal
_h   =  [ \t\f]
__   =  {_v} | {_h}

miChar = [0-9a-zA-Z]
miTexto = \"({__}|{miChar})+\"
//miTextoXt
//miURL
miColor =  #[0-9a-fA-F]{6}
miInteger = [0-9]+
miURL = [0-9a-zA-Z\:\.\/\?\=]+

points = \"{__}"points"{__}\"{__}\: 
lineStyle = \"{__}"lineStyle"{__}\"{__}\: 
data = \"{__}"data"{__}\"{__}\: 
label = \"{__}"label"{__}\"{__}\: 
icon = \"{__}"icon"{__}\"{__}\: 
link = \"{__}"link"{__}\"{__}\: 
title = \"{__}"title"{__}\"{__}\: 
description = \"{__}"description"{__}\"{__}\: 
copyright = \"{__}"copyright"{__}\"{__}\: 
backgroundColor = \"{__}"backgroundColor"{__}\"{__}\: 
fontFamily = \"{__}"fontFamily"{__}\"{__}\: 
fontSize = \"{__}"fontSize"{__}\"{__}\: 
category = \"{__}"category"{__}\"{__}\: 
value = \"{__}"value"{__}\"{__}\: 
color = \"{__}"color"{__}\"{__}\: 
xAxisLabel = \"{__}"xAxisLabel"{__}\"{__}\: 
yAxisLabel = \"{__}"yAxisLabel"{__}\"{__}\: 
legendPosition = \"{__}"legendPosition"{__}\"{__}\: 
x = \"{__}"x"{__}\"{__}\: 
y = \"{__}"y"{__}\"{__}\: 
size = \"{__}"size"{__}\"{__}\: 
header = \"{__}"header"{__}\"{__}\: 
footer = \"{__}"footer"{__}\"{__}\: 
chart = \"{__}"chart"{__}\"{__}\: 
keywords = \"{__}"keywords"{__}\"{__}\: 






%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%
// yybegin(MI_ETIQUETA);

<YYINITIAL> {

{points}               {}
{lineStyle}            {}
{data}                 {}
{link}                 {}
{backgroundColor}      {}
{value}                {}
{color}                {}
{x}                    {}
{y}                    {}
{size}                 {}
{header}               {}
{footer}               {}
{chart}                {}

{label}                {yybegin();}
{icon}                 {yybegin();}
{title}                {yybegin();}
{description}          {yybegin();}
{copyright}            {yybegin();}
{fontFamily}           {yybegin();}
{fontSize}             {yybegin();}
{category}             {yybegin();}
{xAxisLabel}           {yybegin();}
{yAxisLabel}           {yybegin();}
{legendPosition}       {yybegin();}
{keywords}             {yybegin();}

{miInteger}
{miColor} 
{miURL} 
{miTexto}              {}

}

<LX_TEXTO>{

}



// ignorados

// error
[^]  { log("@lexer: ").append(getPunto().toString()).append("\n"); return symbol("",ParserAccionesSym.error); }
