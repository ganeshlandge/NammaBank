import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import { useState } from "react";
const defaultTheme = createTheme();

function AdminPayee(){
    const [state, setState] = useState({
        "curr_is_admin":"",
        "userId":"",
        "payee": ""
    })
    // useEffect(()=>{ 
    //     var getuseridUrl = ({username}) => `http://localhost:8082/netbanking/${username}`;
    //     axios.get(getuseridUrl({username:state.username})).then((res)=>{
    //         var Url = ({userid}) => `http://localhost:8082/user/${userid}`;
    //         const userid = res.data.id
    //         setState((prevProps)=>({
    //             ...prevProps,
    //             ["userId"]:userid
    //         }));
    //         axios.get(Url(userid)).then((resp) => {
    //             setState((prevProps) => ({
    //                 ...prevProps,
    //                 ["is_admin"]:resp.data.is_admin

    //         }))
    //         })
    //     }).catch((error)=>{
    //         console.log(error);
    //     }).catch((error)=>{console.log(error);});
    //     //if not admin redirect back
    // }, []);

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        console.log(name,value);
        setState((prevProps) => ({
          ...prevProps,
          [name]: value
        }));
      };

      const handleSubmit = (event) => {
        event.preventDefault();
        
        var Url = ({userid}) => `http://localhost:8082/user/${userid}`;
        axios.get(Url({userid: state.userId})).then((res) => {
            setState((prevProps) => ({
                ...prevProps,
                payee: res.data?.data?.account?.payee
            }))
            console.log(res)
            console.log(state.transcations);
          
        }).catch((error)=>{
                console.log(error);
        })
        
      };
    

    return(
            <ThemeProvider theme={defaultTheme}>
              <Container component="main" maxWidth="xs">
                <CssBaseline />
                <Box
                  sx={{
                    marginTop: 12,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                  }}
                >
                  <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                   
                  </Avatar>
                  <Typography component="h1" variant="h5">
                    Admin Dashboard
                  </Typography>
                  <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
                  <div className='full-form'>
                  <div className="form-field">
                    <div className='left-col'>
                  <label>ID: </label>
                  </div>
                  <input
                    type="text"
                    name="userId"
                    className='right-col'
                    value={state.userId}
                    onChange={handleInputChange}
                  />
                </div>
                </div>
                    <Button
                      type="submit"
                      fullWidth
                      variant="contained"
                      sx={{ mt: 3, mb: 2, mx: 1, bgcolor: "black", color: "white" }}
                    >
                      Get Payee
                    </Button>
                    {state.payee && (
                        <div>
                            {'\n'}{'\n'}<h2><b>Payees: </b></h2>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Payee Account Number</th>
                                        <th>Name </th>
                                        <th>Nickname</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {state.payee.map((payee, index) => {
                                        return(
                                        <tr key = {index}>
                                            <td>{payee["id"]["payee_acc_num"]}</td>
                                            <td>{payee["name"]}</td>
                                            <td>{payee["nick_name"]}</td>
                                        </tr>);
                                    })}
                                </tbody>
                            </table>
                        </div>
                    )
                    }
                  
                  </Box>
                </Box>
                
              </Container>
            </ThemeProvider>
          );
}

export default AdminPayee;