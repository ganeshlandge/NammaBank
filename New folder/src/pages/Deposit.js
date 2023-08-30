import React, { useState, useEffect } from "react";
import axios from "axios";
import { Box, Button } from "@mui/material";
import { useNavigate } from 'react-router-dom';

function Deposit() {
  const [account_num, setAccountNum] = useState([]);
  const [amount, setAmount] = useState()
  const [remarks, setRemarks] = useState()
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  useEffect(() => {
    const username = sessionStorage.getItem('username');
    var detailsUrl = ({username}) => `http://localhost:8082/netbanking/${username}`;
    console.log(detailsUrl({username : username}));
    axios.get(detailsUrl({username: username}))
      .then((res) => {
        setAccountNum(res.data.account_num)
      })
      
      .catch((err) => {
        console.log(err);
        setError(err);
      });
  }, []);

  const handleAmountChange = (event) => {
    const {value} = event.target;
    setAmount(value);
  }

  const handleRemarksChange = (event) => {
    const {value} = event.target;
    setRemarks(value);
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = {
        "credit_acc_num": parseInt(account_num),
        "amount_transfer": parseInt(amount),
        "remark": remarks
    }
    console.log(data)
    axios.post("http://localhost:8082/deposit", data).then().catch((err)=> console.log(err))
    sessionStorage.setItem("amt", amount)
    navigate("/dashboard/depositSuccess");
  }

  if (error) {
    return (
      <div>
        <p>There was some error fetching your details: {error.message}</p>
      </div>
    );
  }

  return (
    <div>
      <h1><b>Deposit</b></h1>
      <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
      <div className="form-control">
            <label>To Account number:</label>
            <input 
                type="int" 
                name="account_num" 
                defaultValue = {sessionStorage.getItem("account_num")}
                readOnly = {true}
            />
      </div>
      <div className="form-control">
        <label>Amount:</label>
        <input type="int"
        onChange={handleAmountChange}
        defaultValue = {amount}/>
      </div>
      <div className="form-control">
        <label>Remarks:</label>
        <input type="text"
        onChange={handleRemarksChange}
        defaultValue = {remarks}/>
      </div>
      <Button
            type="submit"
            //fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2, mx: 1, bgcolor: "black", color: "white" }}
        >
            Deposit Amount
        </Button>
        </Box>
    </div>
  );
}

export default Deposit;