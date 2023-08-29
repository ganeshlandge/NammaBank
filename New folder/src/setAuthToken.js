import axios from "axios";
const setAuthToken = token => {
    if(token) {
      // Apply authorization token to every request if logged in
        axios.defaults.headers.common["Authorization"] = "Bearer "+ token;
        console.log(axios.headers)
    } else {
        // Delete auth header
        delete axios.defaults.headers.common["Authorization"];
    }
};

export default setAuthToken;