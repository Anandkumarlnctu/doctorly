document.addEventListener("DOMContentLoaded", () => {
    const ele = document.getElementById("appointment-book");
    if (ele) {
        ele.addEventListener("click", () => {
            window.location.href = "appointment.html";
        });
    }

    const registerLink = document.getElementById("register-link");
    const loginLink = document.getElementById("login-link");

    const submitbtn = document.getElementById("submit");
    if (submitbtn) {
        submitbtn.addEventListener("click", () => {
			event.preventDefault(); // Prevent default form submission
			    console.log("Submit button clicked!");
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;
            const email = document.getElementById("email").value;

            const data = { username, password, email };
            const jsondata = JSON.stringify(data);

            fetch('http://localhost:8080/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: jsondata
            })
            .then(response => {
                if (response.status == 200) {
                    alert('Registration successful');
					setTimeout(() => {
					    window.location.href = '/home';
					}, 1000);
                } else {
                    alert('Registration failed');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Something went wrong.');
            });
        });
    }
});

const submitlogin=document.getElementById("submit-login")
if(submitlogin){
	submitlogin.addEventListener("click", async function (event) {
	        event.preventDefault();
	        console.log("clicked");

	        const username = document.getElementById('username').value;
	        const password = document.getElementById('password').value;

	        try {
	            const response = await fetch('/auth/login', {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                body: JSON.stringify({ username,password })
	            });

	            if (response.ok) {
	                const data = await response.json();
	                const token = data.token;
	                console.log(token);

	                // Store the JWT in localStorage
					// Store the JWT in cookies, only if not already set
					
					if (!document.cookie.includes('jwt')) {
					    document.cookie = `jwt=${token}; path=/authhome;`;
						document.cookie = `jwt=${token}; path=/hospital_list;`;
						
						
						
					} else {
					    // Remove the old JWT cookie
					    document.cookie = 'jwt=; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT;';
					    
					    // Set the new JWT token cookie
					    document.cookie = `jwt=${token}; path=/;`;
					}
					
	                 window.location.href="/authhome"
					

	                // Redirect to the dashboard
	               
				   
	                
	            } else {
	                // Display error message
	                const errorMessage = document.getElementById('errorMessage');
	                if (errorMessage) {
	                    errorMessage.style.display = 'block';
	                }
	            }
	        } catch (error) {
	            console.error('Error logging in:', error);
	        }
	    });
	}

	























