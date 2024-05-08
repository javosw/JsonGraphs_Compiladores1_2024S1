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

    private Symbol symbol(int sym) {
        printLexema(sym); // DEBUG
        int izq = (int)yychar+1;
        int der = (int)yychar+yylength();
        String name = ParserDashbSym.terminalNames[sym];
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der);
        return mySymbol;
    }
    private Symbol symbol(int sym, Object val) {
        printLexema(sym); // DEBUG
        int izq = (int)yychar+1;
        int der = (int)yychar+yylength();
        String name = ParserDashbSym.terminalNames[sym];
        Symbol mySymbol = myFactory.newSymbol(name, sym, izq, der, val);
        return mySymbol;
    }

    public class Punto
    {
        private long r1;
        private long r2;
        private int pline;
        private int pcolumn;

        public Punto(long pchar, int plength, int pline, int pcolumn)
        {
            this.r1 = pchar;
            this.r2 = pchar + (plength - 1);
            this.pline = pline;
            this.pcolumn = pcolumn;
        }
        
        public String getValues()
        {
            String tr = "r="+r1+"-"+r2;
            String tline = "y="+pline;
            String tcolumn = "x="+pcolumn;
            return tr+", "+tline+", "+tcolumn;
        }
    }

    // para errores lexicos
    private StringBuilder log = new StringBuilder();
    private StringBuilder log(String text) { return log.append(text); }

    // para debug
    private void print(String texto){ System.out.print(texto); } 
    public Punto puntoActual(){ return new Punto((yychar+1),yylength(),(yyline+1),(yycolumn+1)); };
    private void printLexema(int sym) { print(ParserDashbSym.terminalNames[sym]+" ("+yytext()+"): "+puntoActual().getValues()+"\n"); }

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

// áÁ = \u00E1\u00C1  \341\301
// éÉ = \u00E9\u00C9  \351\311
// íÍ = \u00ED\u00CD  \355\315
// óÓ = \u00F3\u00D3  \363\323
// úÚ = \u00FA\u00DA  \372\332
// ñÑ = \u00F1\u00D1  \361\321
// © = \u00A9

// áÁéÉíÍóÓúÚñÑ
// \341\301\351\311\355\315\363\323\372\332\361\321
// \u00E1\u00C1\u00E9\u00C9\u00ED\u00CD\u00F3\u00D3\u00FA\u00DA\u00F1\u00D1\u00A9
//miChar = [0-9a-zA-Z\,\.\-\_áÁéÉíÍóÓúÚñÑ©]
miChar = [0-9a-zA-Z\,\.\-\_\u00E1\u00C1\u00E9\u00C9\u00ED\u00CD\u00F3\u00D3\u00FA\u00DA\u00F1\u00D1\u00A9]
miTexto = \"({__}|{miChar})+\"
//miTextoXt
//miURL

miColor =  \"{__}*#[0-9a-fA-F]{6}{__}*\"
miInteger = [0-9]+
miURL = \"{__}*(("http"|"https")\:\/\/)[0-9a-zA-Z\:\.\/\?\=\-\_]+{__}*\"

points = \"{__}*"points"{__}*\"{__}*\: 
lineStyle = \"{__}*"lineStyle"{__}*\"{__}*\: 
data = \"{__}*"data"{__}*\"{__}*\: 
label = \"{__}*"label"{__}*\"{__}*\: 
icon = \"{__}*"icon"{__}*\"{__}*\: 
link = \"{__}*"link"{__}*\"{__}*\: 
title = \"{__}*"title"{__}*\"{__}*\: 
description = \"{__}*"description"{__}*\"{__}*\: 
copyright = \"{__}*"copyright"{__}*\"{__}*\: 
backgroundColor = \"{__}*"backgroundColor"{__}*\"{__}*\: 
fontFamily = \"{__}*"fontFamily"{__}*\"{__}*\: 
fontSize = \"{__}*"fontSize"{__}*\"{__}*\: 
category = \"{__}*"category"{__}*\"{__}*\: 
value = \"{__}*"value"{__}*\"{__}*\: 
color = \"{__}*"color"{__}*\"{__}*\: 
xAxisLabel = \"{__}*"xAxisLabel"{__}*\"{__}*\: 
yAxisLabel = \"{__}*"yAxisLabel"{__}*\"{__}*\: 
legendPosition = \"{__}*"legendPosition"{__}*\"{__}*\: 
x = \"{__}*"x"{__}*\"{__}*\: 
y = \"{__}*"y"{__}*\"{__}*\: 
size = \"{__}*"size"{__}*\"{__}*\: 
header = \"{__}*"header"{__}*\"{__}*\: 
footer = \"{__}*"footer"{__}*\"{__}*\: 
chart = \"{__}*"chart"{__}*\"{__}*\: 
keywords = \"{__}*"keywords"{__}*\"{__}*\: 
name = \"{__}*"name"{__}*\"{__}*\:

%%
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% %%%%%%%%%%%%%%%%%%%%
// yybegin(CONTEXTO_1);

<YYINITIAL> {

{x}                    { return symbol(ParserDashbSym.KD_X); }
{y}                    { return symbol(ParserDashbSym.KD_Y); }
{data}                 { return symbol(ParserDashbSym.KD_DATA); }
{icon}                 { return symbol(ParserDashbSym.KD_ICON); }
{link}                 { return symbol(ParserDashbSym.KD_LINK); }
{size}                 { return symbol(ParserDashbSym.KD_SIZE); }
{name}                 { return symbol(ParserDashbSym.KD_NAME); }
{value}                { return symbol(ParserDashbSym.KD_VALUE); }
{color}                { return symbol(ParserDashbSym.KD_COLOR); }
{chart}                { return symbol(ParserDashbSym.KD_CHART); }
{label}                { return symbol(ParserDashbSym.KD_LABEL); }
{title}                { return symbol(ParserDashbSym.KD_TITLE); }
{header}               { return symbol(ParserDashbSym.KD_HEADER); }
{footer}               { return symbol(ParserDashbSym.KD_FOOTER); }
{points}               { return symbol(ParserDashbSym.KD_PONTS); }
{keywords}             { return symbol(ParserDashbSym.KD_KEYWORDS); }
{fontSize}             { return symbol(ParserDashbSym.KD_FONTSIZE); }
{category}             { return symbol(ParserDashbSym.KD_CATEGORY); }
{lineStyle}            { return symbol(ParserDashbSym.KD_LINE); }
{copyright}            { return symbol(ParserDashbSym.KD_COPYR); }
{fontFamily}           { return symbol(ParserDashbSym.KD_FONTFAM); }
{xAxisLabel}           { return symbol(ParserDashbSym.KD_XLABEL); }
{yAxisLabel}           { return symbol(ParserDashbSym.KD_YLABEL); }
{description}          { return symbol(ParserDashbSym.KD_DESCRIP); }
{legendPosition}       { return symbol(ParserDashbSym.KD_LEGEND); }
{backgroundColor}      { return symbol(ParserDashbSym.KD_BACKGR); }

{miInteger}            { return symbol(ParserDashbSym.MI_INTEGER,yytext()); }
{miColor}              { return symbol(ParserDashbSym.MI_COLOR,yytext()); }
{miURL}                { return symbol(ParserDashbSym.MI_URL,yytext()); }
{miTexto}              { return symbol(ParserDashbSym.MI_TEXTO,yytext()); }

}

"{"  { return symbol(ParserDashbSym.IZQLLAVE); }
"}"  { return symbol(ParserDashbSym.DERLLAVE); }
"["  { return symbol(ParserDashbSym.IZQCORCH); }
"]"  { return symbol(ParserDashbSym.DERCORCH); }
","  { return symbol(ParserDashbSym.COMA); }

// ignorados
{__}+ {}
// error
[^]  {
    //print("@error: "+yytext()+","+puntoActual().getValues()+"\n");
    return symbol(ParserDashbSym.error);
}

