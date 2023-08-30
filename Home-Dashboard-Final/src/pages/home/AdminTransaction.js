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
function AdminTransaction(){
    const [state, setState] = useState({
        "curr_is_admin":"",
        "userId":"",
        "id": "",
        email: "",
        "mobile_num":"",
        "adhar_id": "",
        "title": "",
        "first_name": "",
        "middle_name": "",
        "father_name": "",
        "last_name": "",
        "dob": "",
        "age": "",
        "gender": "",
        "perm_add_id": "",
        "temp_add_id": "",
        "is_admin": "",
        "occupation_type": "",
        "income_source": "",
        "annual_income":  "",
        "account_num": "",
        "username": "",
        "login_passwd": "",
        "transcation_passwd": "",
        "balance": "",
        "open_date": "",
        "account_type": "",
        "is_debit_card": "",
        "is_credit_card": "",
        "is_net_banking": "",
        "timestamp": "",
        "transcations": "",
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
                ["id"]: res.data?.data?.id,
                email: res.data?.data?.email,
                mobile_num: res.data?.data?.mobile_num,
                adhar_id: res.data?.data?.adhar_id,
                title: res.data?.data?.title,
                first_name: res.data?.data?.first_name,
                middle_name: res.data?.data?.middle_name,
                father_name: res.data?.data?.father_name,
                last_name: res.data?.data?.last_name,
                dob: res.data?.data?.dob,
                age: res.data?.data?.age,
                gender: res.data?.data?.gender,
                perm_add_id: res.data?.data?.perm_add_id,
                temp_add_id: res.data?.data?.temp_add_id,
                is_admin: res.data?.data?.is_admin,
                occupation_type: res.data?.data?.occupation_type,
                income_source: res.data?.data?.income_source,
                annual_income: res.data?.data?.annual_income,
                account_num: res.data?.data?.account?.account_num,
                username: res.data?.data?.account?.username,
                login_passwd: res.data?.data?.account?.login_passwd,
                transcation_passwd: res.data?.data?.account?.transcation_passwd,
                balance: res.data?.data?.account?.balance,
                open_date: res.data?.data?.account?.open_date,
                account_type: res.data?.data?.account?.account_type,
                is_debit_card: res.data?.data?.account?.is_debit_card,
                is_credit_card: res.data?.data?.account?.is_credit_card,
                is_net_banking: res.data?.data?.account?.is_net_banking,
                timestamp: res.data?.data?.account?.timestamp,
                transcations: res.data?.data?.account?.transcations,
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
                      sx={{ mt: 3, mb: 2 }}
                    >
                      Get Details
                    </Button>

                    {state.id && (
                        <div>
                            <h2> User details:</h2>
                            <table><tbody>

                            <tr>
                                <td>id:</td>
                                <td>{state.id}</td>
                            </tr>
                            <tr>
                                <td>email: </td><td>{state.email}</td>
                            </tr>
                            <tr>
                            <td>mobile_num:</td><td>{state.mobile_num}</td>
                            </tr>
                            <tr>
                                <td>adhar_id:</td> <td>{state.adhar_id}</td>
                            </tr>
                            <tr>
                                <td>name:</td> <td>{state.title +" " + state.first_name + " " + state.middle_name + " " + state.last_name }</td>
                            </tr>
                            <tr>
                                <td>father_name:</td> <td>{state.father_name}</td>
                            </tr>
                            <tr>
                               <td> dob:</td><td> {state.dob}</td>
                            </tr>
                            <tr>
                                <td>age: </td><td>{state.age}</td>
                                </tr>
                            <tr>
                                <td>gender:</td> <td>{state.gender}</td>
                                </tr>
                            <tr>
                                <td>perm_add_id: </td><td>{state.perm_add_id}</td>
                                </tr>
                            <tr><td>temp_add_id:</td> <td>{state.temp_add_id}</td>
                            </tr>
                            <tr><td>is_admin:</td> <td>{state.is_admin}</td>
                            </tr>
                            <tr> <td>occupation_type: </td> <td>{state.occupation_type}</td></tr>
                            <tr><td>income_source: </td> <td> {state.income_source}</td></tr>
                            <tr><td>annual_income: </td> <td>{state.annual_income}</td></tr>
                            <tr><td>account_num:</td> <td>{state.account_num}</td></tr>
                            <tr><td>username:</td><td> {state.username}</td></tr>
                            <tr><td>balance:</td> <td>{state.balance}</td></tr>
                            <tr><td>open_date:</td> <td>{state.open_date}</td></tr>
                            <tr><td>account_type:</td> <td>{state.account_type}</td></tr>
                            <tr><td>is_debit_card:</td> <td>{state.is_debit_card}</td></tr>
                            <tr> <td>is_credit_card:</td> <td>{state.is_credit_card}</td></tr>
                            <tr><td>is_net_banking:</td> <td>{state.is_net_banking}</td></tr>
                            <tr><td>timestamp:</td> <td>{state.timestamp}</td></tr>
                            </tbody></table>
                        </div>
                    )}
                    {state.transcations && (
                        <div>
                            <h2>Transactions: </h2>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Transaction ID</th>
                                        <th>Transaction Type: </th>
                                        <th>Amount Transfer</th>
                                        <th>Credit Account Number</th>
                                        <th>Timestamp</th>
                                        <th>Remark</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {state.transcations.map((transaction, index) => {
                                        return(
                                        <tr key = {index}>
                                            <td>{transaction["transcaction_id"]}</td>
                                            <td>{transaction["transcation_type"]}</td>
                                            <td>{transaction["amount_transfer"]}</td>
                                            <td>{transaction["credit_acc_num"]}</td>
                                            <td>{transaction["timestamp"]}</td>
                                            <td>{transaction["remark"]}</td>
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

export default AdminTransaction;