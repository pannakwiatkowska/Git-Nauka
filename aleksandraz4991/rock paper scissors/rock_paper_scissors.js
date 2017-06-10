var userChoice = prompt("Do you choose rock, paper, scissors or rope?");


var computerChoice = Math.random();
if (computerChoice < 0.26) {
	computerChoice = "rock";
} else if(computerChoice <= 0.51) {
	computerChoice = "paper";
} else if(computerChoice <= 0.76) {
	computerChoice = "scissors";
} else {
    computerChoice = "rope";
} console.log("Computer: " + computerChoice);



var compare = function(choice1, choice2) {
    if (choice1 === choice2) {
        // return "The result is a tie!";
            var secondUserChoice = prompt("Do you choose rock, paper, scissors or rope?");
            var secondComputerChoice = Math.random();
            if (computerChoice < 0.26) {
                computerChoice = "rock";
            } else if(computerChoice <= 0.51) {
                computerChoice = "paper";
            } else if(computerChoice <= 0.76) {
                computerChoice = "scissors";
            } else {
                computerChoice = "rope";
            } console.log("Computer: " + computerChoice);
            return compare(secondUserChoice, secondComputerChoice);
    } else if (choice1 === "rock") {
        if (choice2 === "scissors") {
            return "rock wins";
        } else  if (choice2 === "paper") {
            return "paper wins";
        } else {
            return "rope wins";
        }
    } else if (choice1 === "paper") {
        if (choice2 === "rock") {
            return "paper wins";
        } else if (choice2 === "scissors") {
            return "scissors wins";
        } else {
            return "paper wins";
        }
    } else if (choice1 === "scissors") {
        if (choice2 === "rock") {
            return "rock wins";   
        } else if (choice2 === "paper") {
            return "scissors wins";
        } else {
            return "scissors wins";
        }
    } else if (choice1 === "rope") {
        if (choice2 === "rock") {
            return "rope wins";
        } else if (choice2 === "paper") {
            return "paper wins";
        } else {
            return "scissors wins";
        }
        
    } else {
        return "Wrong word!";
    }
};


compare(userChoice, computerChoice);


// https://www.codecademy.com/courses/javascript-beginner-en-Bthev-mskY8/1/5?curriculum_id=506324b3a7dffd00020bf661

/*  
01. What if a user makes an inappropriate choice like 'dog'? How can we extend the function to handle that? 
CHECKED
*/


/*
02. What if players in the game could also choose Rope in this game?
CHECKED
*/


/*
03. In this version, if both players make the same choice, the game returns a tie. What if the game didn't end there but instead asked both players for new choices? 
CHECKED
*/