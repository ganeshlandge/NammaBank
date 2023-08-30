import React, { useState, useEffect } from "react";
import axios from "axios";

function AccountStatement() {
  const [transactions, setTransactions] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const account_num = sessionStorage.getItem('account_num');
    var detailsUrl = ({account_num}) => `http://localhost:8082/summary/${account_num}`;
    console.log(detailsUrl({account_num : account_num}));
    axios.get(detailsUrl({account_num : account_num}))
      .then((res) => {
        setTransactions(res.data?.data);
        console.log(transactions);
      })
      
      .catch((err) => {
        console.log(err);
        setError(err);
      });
  }, []);

  if (error) {
    return (
      <div>
        <p>There was some error fetching your transactions: {error.message}</p>
      </div>
    );
  }

  if (transactions.length === 0) {
    return <p>No transactions available.</p>;
  }

  return (
    <div>
      <h1><b>Account Statement</b></h1>
      <table>
        <thead>
          <tr>
            <th>Transaction ID</th>
            <th>Transaction Type</th>
            <th>Amount Transfer</th>
            <th>Credit Account Number</th>
            <th>Transaction Date</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction, index) => (
            <tr key={index}>
              <td>{transaction["transcaction_id"]}</td>
              <td>{transaction["transcation_type"] ? transaction["transcation_type"] : "Deposit" }</td>
              <td>{transaction["amount_transfer"]}</td>
              <td>{transaction["credit_acc_num"]}</td>
              <td>{new Date(transaction["timestamp"]).toString().split(" ").slice(1,4).join(" ")}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AccountStatement;