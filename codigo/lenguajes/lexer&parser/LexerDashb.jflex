// meta characters:  |  (  )  {  }  [  ]  < >  \  .  *  +  ?  ^  $  / . " ~ !

// codigo antes de la clase lexer
package josq.lenguajes.automatas;

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

    public class Punto
    {
        public int yycolumn;
        public int yyline;
        public int yylength;
        public int yychar;

        public Punto(int yycolumn, int yyline, int yylength, int yychar)
        {
            this.yycolumn = yycolumn;
            this.yyline = yyline;
            this.yylength = yylength;
            this.yychar = yychar;
        }

        @Override
        public String toString()
        {
            return "line="+yyline+",col="+yycolumn+",leng="+yylength+",char="+yychar;
        }
        
        public String getValues()
        {
            return "y="+yyline+",x="+yycolumn+",l="+yylength+",c="+yychar;
        }
    }

    public Punto getPuntoActual(){ return new Punto(yycolumn, yyline, yylength(), (int)yychar+1); };

    // para errores lexicos
    private StringBuilder log = new StringBuilder();
    private StringBuilder log(String text) { return log.append(text); }

    
    /*
    ComplexSymbolFactory myFactory = null;

    public Lexer(Reader in, ComplexSymbolFactory sf) { this(in); myFactory = sf; }

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

// macros para regex

// invisible vertical
_v   =  \r|\n|\r\n
// invisible horizontal
_h   =  [ \t\f]
__   =  {_v}|{_h}

miChar = [0-9a-zA-ZáéíóúñÁÉÍÓÚÑ]
miTexto = \"({__}|{miChar})+\"
//miTextoXt
//miURL

miColor =  \"{__}#[0-9a-fA-F]{6}{__}\"
miInteger = [0-9]+
miURL = \"{__}*[0-9a-zA-Z\:\.\/\?\=]+{__}*\"

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
name = \"{__}"name"{__}\"{__}\:

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%
// yybegin(MI_ETIQUETA);

<YYINITIAL> {

{data}                 { return symbol("",ParserDashbSym.KD_DATA); }
{points}               { return symbol("",ParserDashbSym.KD_PONTS); }
{lineStyle}            { return symbol("",ParserDashbSym.KD_LINE); }
{link}                 { return symbol("",ParserDashbSym.KD_LINK); }
{backgroundColor}      { return symbol("",ParserDashbSym.KD_BACKGR); }
{value}                { return symbol("",ParserDashbSym.KD_VALUE); }
{color}                { return symbol("",ParserDashbSym.KD_COLOR); }
{x}                    { return symbol("",ParserDashbSym.KD_X); }
{y}                    { return symbol("",ParserDashbSym.KD_Y); }
{size}                 { return symbol("",ParserDashbSym.KD_SIZE); }
{header}               { return symbol("",ParserDashbSym.KD_HEADER); }
{footer}               { return symbol("",ParserDashbSym.KD_FOOTER); }
{chart}                { return symbol("",ParserDashbSym.KD_CHART); }

{label}                { return symbol("",ParserDashbSym.KD_LABEL); }
{icon}                 { return symbol("",ParserDashbSym.KD_ICON); }
{title}                { return symbol("",ParserDashbSym.KD_TITLE); }
{description}          { return symbol("",ParserDashbSym.KD_DESCRIP); }
{copyright}            { return symbol("",ParserDashbSym.KD_COPYR); }
{fontFamily}           { return symbol("",ParserDashbSym.KD_FONTFAM); }
{fontSize}             { return symbol("",ParserDashbSym.KD_FONTSIZE); }
{category}             { return symbol("",ParserDashbSym.KD_CATEGORY); }
{xAxisLabel}           { return symbol("",ParserDashbSym.KD_XLABEL); }
{yAxisLabel}           { return symbol("",ParserDashbSym.KD_YLABEL); }
{legendPosition}       { return symbol("",ParserDashbSym.KD_LEGEND); }
{keywords}             { return symbol("",ParserDashbSym.KD_KEYWORDS); }
{name}                 { return symbol("",ParserDashbSym.KD_NAME); }

{miInteger}            { return symbol("",ParserDashbSym.MI_INTEGER); }
{miColor}              { return symbol("",ParserDashbSym.MI_COLOR); }
{miURL}                { return symbol("",ParserDashbSym.MI_URL); }
{miTexto}              { return symbol("",ParserDashbSym.MI_TEXTO); }

}

"{"  { return symbol("",ParserDashbSym.IZQLLAVE); }
"}"  { return symbol("",ParserDashbSym.DERLLAVE); }
"["  { return symbol("",ParserDashbSym.IZQCORCH); }
"]"  { return symbol("",ParserDashbSym.DERCORCH); }
","  { return symbol("",ParserDashbSym.COMA); }

// ignorados
{__} {}
// error
[^]  { log("@lexer: ").append(getPuntoActual().toString()).append("\n"); return symbol("",ParserDashbSym.error); }

