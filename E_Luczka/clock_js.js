$(document).ready(function() {
	const secondHand = document.querySelector('.second-hand');
	const minsHand = document.querySelector('.min-hand');
	const hourHand = document.querySelector('.hour-hand');

	function setDate() {
		
		const now = new Date();
		
		//seconds
		const seconds = now.getSeconds();
		const secondsDegress = ((seconds / 60) * 360) +90;
		secondHand.style.transform = `rotate(${secondsDegress}deg)`;
		console.log(seconds);  //TO REMOVE
		
		//minutes
		const mins = now.getMinutes();
		const minsDegress = ((mins / 60) * 360) +90;
		minsHand.style.transform = `rotate(${minsDegress}deg)`;
		console.log(mins);  //TO REMOVE
		
		//TO DO: hour doesn't work
		const hour = now.getHours();
		const hourDegress = ((mins / 12) * 360) +90;
		hourHand.style.transform = `rotate(${hourDegress}deg)`;
		console.log(hour);  //TO REMOVE
	}

	setInterval(setDate, 1000);
}); 