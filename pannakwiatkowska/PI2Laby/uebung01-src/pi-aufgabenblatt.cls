% -----------------------------------------------------------------------
% pi-aufgabenblatt.cls
% Based on pi2-muster.tex and defs.tex used before 2003 for
% PI 2 in Bremen.
% Adapted by Thomas Röfer
% Further adapted by Kai-Florian Richter
% Further adapted by Hannes Baumann and Sebastian Offermann

\NeedsTeXFormat{LaTeX2e}
\ProvidesClass{pi-aufgabenblatt}
\LoadClass[11pt,a4paper,twoside]{article}
\usepackage[ngerman]{babel}
\usepackage[hidelinks]{hyperref}
\usepackage{color}
\usepackage{fancyhdr}
\usepackage{graphicx}
\usepackage[utf8]{inputenc}
\usepackage{listingsutf8}
\usepackage{url}
\usepackage[usenames,dvipsnames]{xcolor}

\lstset{numberstyle=\scriptsize}

% -----------------------------------------------------------------------
% Page layout

% enlarge page
\setlength{\textwidth}{16cm}
\setlength{\topmargin}{-1cm}
\setlength{\evensidemargin}{0cm}
\setlength{\oddsidemargin}{0cm}
\setlength{\textheight}{24cm}

% skip between paragraphs
\setlength{\parskip}{1ex}
% ... and no indentation at start of a new paragraph
\setlength{\parindent}{0ex}

% Course settings
\newcommand{\thissemester}{SoSe 2017}
\newcommand{\thistitle}{Praktische Informatik 2}
\newcommand{\thisshorttitle}{PI 2} % used in headings

% Format commands
\newcommand{\C}[1]{\texttt{\mbox{#1}}} % classes
\newcommand{\m}[1]{\emph{\mbox{#1}}} % methods
\newcommand{\p}[1]{\emph{\mbox{#1}}} % parameters, variables
\newcommand{\key}[1]{\textbf{#1}} % keywords
\newcommand{\sg}[1]{\m{#1}} % setter/getter
\newcommand{\setter}{\sg{setter}-Methode}
\newcommand{\getter}{\sg{getter}-Methode}
\newcommand{\str}[1]{\texttt{"#1"}} % Strings
\newcommand{\skipInput}[1]{}

\newcommand{\kommentar}[1]{}
\newcommand{\todo}[1]{\textcolor{red}{TODO: #1}}

% Format commands for exercises
\newcommand{\points}[1]{[#1 Punkt\ifnum#1=1\else e\fi]}
\newcommand{\pointstotal}[1]{\textcolor{gray}{\points{#1}}}
\newcommand{\optional}{[optional]}

\newenvironment{changesEnv}{

  \line(1,0){250}
  
  \paragraph{Änderungen:}}{

\line(1,0){250}

}

\ifdefined\tutorInfoFlag
  \newcommand{\changes}[1]{
  
  \line(1,0){250}
  
  \paragraph{Änderungen:} #1 
  
  \line(1,0){250}

}
\else
  \newcommand{\changes}[1]{}
\fi

\newenvironment{tutorInfoEnv}{

  \line(1,0){250}
  
  \paragraph{Tutor Info:}}{

\line(1,0){250}

}

\ifdefined\tutorInfoFlag
  \newcommand{\tutorInfo}[1]{
  
  \line(1,0){250}
  
  \paragraph{Tutor Info:} #1 
  
  \line(1,0){250}

}
\else
  \newcommand{\tutorInfo}[1]{}
\fi


\tolerance 1414
\hbadness 1414
\emergencystretch 1.5em
\hfuzz 0.3pt
\widowpenalty=10000
\vfuzz \hfuzz
\raggedbottom

% -----------------------------------------------------------------------
% Main macros

\renewcommand{\maketitle}[2]
          {\@maketitle{#1}{#2}{Übungsblatt}}
\renewcommand\thesection{Aufgabe \arabic{section}}
\newcommand\skipsection{\stepcounter{section}}

% -----------------------------------------------------------------------
% Misc macros

\newcommand{\grenzop}[3]{\mathop{#1}\limits^{#2}_{#3}}
\newcommand{\langrechtspfeil}[2]{\grenzop{\longrightarrow}{#1}{#2}}
\newcommand{\bob}[0]{\hspace*{\fill}$\Box$}

% -----------------------------------------------------------------------

\renewcommand{\@maketitle}[3]
{
    \begin{tabular}[t]{lr}
      \begin{tabular}[t]{l}
        \begin{tabular}[t]{l@{\hskip 10mm}l}
            \thistitle & \thissemester \hspace{4ex} \\
        \end{tabular} \vspace{5mm} \\
        {\Huge\sf {#3} {#1} } \\[1.5ex]
        {\Large Abgabe bis: {#2}} \\[1ex]
      \end{tabular}
      &
      \begin{tabular}[t]{l}
      \end{tabular} \\[2ex]
    \end{tabular}\\
    \rule{\linewidth}{1pt}

% -----------------------------------------------------------------------
% Create headings (Thanks to Elmar Loos)

    \fancyhead{} %clear all fields
    \fancyhead[LO,RE]{\thisshorttitle, \thissemester}
    \fancyfoot{} %clear all fields
    \fancyfoot[RO,LE]{\thepage}
}

\endinput
