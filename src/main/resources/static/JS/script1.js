// Function to extract the JWT from cookies
	function getCookie(name) {
	    const value = `; ${document.cookie}`;
	    const parts = value.split(`; ${name}=`);
	    if (parts.length === 2) return parts.pop().split(';').shift();
	    return null;
	}

	// Get the JWT from cookies
	const token = getCookie('jwt'); // "jwt" is the name of your cookie

	if (token) {
	    try {
	        // Decode the token
	        const decodedToken = jwt_decode(token);

	        // Extract the username from the decoded token (assuming it's in the 'sub' field)
	        const username = decodedToken.sub; // 'sub' is typically the username in a JWT

	        // Display the username inside the <p> tag with id 'username'
	        document.getElementById('username').innerText = `Welcome, ${username}`;
	    } catch (error) {
	        console.error('Invalid or expired token:', error);
	    }
	} else {
	    // Redirect to login page if no JWT token is found
	    window.location.href = "/signin";
	}

