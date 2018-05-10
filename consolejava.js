var homework = {};
homework.fibonacci = function (n) {
	var result = [0,1];
	for(i=2;i<=n;i++){
		result[i] = result[i-1] + result[i-2];
	}
	return result[n];
}
homework.sort = function(array) {
	for(i = 0; i<array.length; i++){
		if(array[i] > array[i+1]) {
			temp = array[i];
			array[i] = array[i+1];
			array[i+1] = temp;
			return homework.sort(array);
		}
	}
	return array;
}
homework.factorial = function(n) {
	var result = 1;
	for(i=n;i>0;i--){
		result = result * i;
	}
	return result;
}
homework.rotateLeft = function(array,n) {
	for(j=0; j<n;j++) {
		temp = array[0];
		for(i=0; i<array.length; i++) {
			array[i] = array[i+1]
		}
		array[array.length - 1] = temp;
	}
	return array;
}
homework.balancedBrackets = function test(bracketsString) {
	
	num1 = bracketsString.indexOf("]")
	num2 = bracketsString.indexOf(")")
	num3 = bracketsString.indexOf("}")

	if(num1 == -1)
		num1 = bracketsString.length;
	if(num2 == -1)
		num2 = bracketsString.length;
	if(num3 == -1)
		num3 = bracketsString.length;
	
	closingParen = Math.min(num1, num2,num3);
	
	if(bracketsString.charAt(closingParen-1) === "(" && bracketsString.charAt(closingParen) === ")"){
		if(bracketsString.length == closingParen+1)
			bracketsString = "";
		else
			bracketsString = bracketsString.slice(0,closingParen-1) + bracketsString.slice(closingParen+1, bracketsString.length);
	}
	
	else if(bracketsString.charAt(closingParen-1) === "\{" && bracketsString.charAt(closingParen) === "\}"){
		if(bracketsString.length == closingParen+1)
			bracketsString = "";
		else
			bracketsString = bracketsString.slice(0,closingParen-1) + bracketsString.slice(closingParen+1, bracketsString.length);
	}
	
	else if(bracketsString.charAt(closingParen-1) === "[" && bracketsString.charAt(closingParen) === "]"){
		if(bracketsString.length == closingParen+1)
			bracketsString = "";
		else
			bracketsString = bracketsString.slice(0,closingParen-1) + bracketsString.slice(closingParen+1, bracketsString.length);
	}
	
	else{
		return false;
	}

	if(bracketsString.length == 0){
		return true;}
	
	else if(bracketsString.length == 1)
		return false;
	else 
		return test(bracketsString);
}