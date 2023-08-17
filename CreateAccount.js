import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useState } from "react";
function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Banking System
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

// TODO remove, this demo shouldn't need to reset the theme.

const defaultTheme = createTheme();

export default function CreateAccount() {
  const [state, setState] = useState({
  'email': "",
  'Mobile Number': ""
});

const handleInputChange = (event) => {
  const { name, value } = event.target;
  setState((prevProps) => ({
    ...prevProps,
    [name]: value
  }));
};

const handleSubmit = (event) => {
  event.preventDefault();
  console.log(state);
};

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Login
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <div className="form-control">
          <label>Email</label>
          <input
            type="text"
            name="email"
            value={state.email}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Mobile Number</label>
          <input
            type="int"
            name="Mobile Number"
            value={state.password}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Aadhaar Number</label>
          <input
            type="int"
            name="Aadhaar Number"
            value={state.aadhaarnumber}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Title</label>
          <input
            type="text"
            name="Title"
            value={state.title}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>First Name</label>
          <input
            type="text"
            name="First Name"
            value={state.firstname}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Middle Name</label>
          <input
            type="text"
            name="Middle Name"
            value={state.middlename}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Last Name</label>
          <input
            type="text"
            name="Last Name"
            value={state.lastname}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Father's Name</label>
          <input
            type="text"
            name="Father's Name"
            value={state.fathersname}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Date of Birth</label>
          <input
            type="text"
            name="Date of Birth"
            value={state.dateofbirth}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Age</label>
          <input
            type="int"
            name="Age"
            value={state.age}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Gender</label>
          <input
            type="text"
            name="Gender"
            value={state.gender}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Permanent Address</label>
          <input
            type="text"
            name="Permanent Address"
            value={state.permanentaddress}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Temporary Address</label>
          <input
            type="text"
            name="Temporary Address"
            value={state.temporaryaddress}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Admin</label>
          <input
            type="text"
            name="Admin"
            value={state.admin}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Occupation Type</label>
          <input
            type="text"
            name="Occupation Type"
            value={state.occupationtype}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Source of Income</label>
          <input
            type="text"
            name="Source of Income"
            value={state.sourceofincome}
            onChange={handleInputChange}
          />
          </div>
          <div className="form-control">
          <label>Annual Income</label>
          <input
            type="int"
            name="Annual Income"
            value={state.annualincome}
            onChange={handleInputChange}
          />
          </div>
            <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="Remember me"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Create Account
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                
                </Link>
              </Grid>
              <Grid item>
                <Link href="#" variant="body2">
                  
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
        <Copyright sx={{ mt: 8, mb: 4 }} />
      </Container>
    </ThemeProvider>
  );
}