function AdminUsers() {
    const [users, setUsers] = useState(null)
    useEffect(()=>{
        axios.get("http://localhost:8082/users").then((res)=>{
            setUsers(res.data?.data)
        })
    })
    return(
        <div>
      <h1><b>Users</b></h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Mobile Number</th>
            <th>Aadhaar ID</th>
            <th>Title</th>
            <th>First Name</th>
            <th>Middle Name</th>
            <th>Last Name</th>
            <th>Father's Name</th>
            <th>Date of Birth</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Permanent Address</th>
            <th>Temporary Address</th>
            <th>Is Admin</th>
            <th>Occupation Type</th>
            <th>Income Source</th>
            <th>Annual Income</th>
            <th>Account Number</th>
            <th>Username</th>
            <th>Balance</th>
            <th>Open Date</th>
            <th>Account Type</th>
            <th>Credit Card </th>
            <th>Debit Card</th>
            <th>Netbanking</th>
          </tr>
        </thead>
        <tbody>
            {users.map((user, index) => {
                return(
                    <tr key={index}>
                        <td>{user["id"]}</td>
                        <td>{user["email"]}</td>
                        <td>{user["mobile_num"]}</td>
                        <td>{user["adhar_id"]}</td>
                        <td>{user["title"]}</td>
                        <td>{user["first_name"]}</td>
                        <td>{user["middle_name"]}</td>
                        <td>{user["last_name"]}</td>
                        <td>{user["father_name"]}</td>
                        <td>{user["dob"]}</td>
                        <td>{user["age"]}</td>
                        <td>{user["gender"]}</td>
                        <td>{user["perm_add_id"]}</td>
                        <td>{user["temp_add_id"]}</td>
                        <td>{user["is_admin"]}</td>
                        <td>{user["occupation_type"]}</td>
                        <td>{user["income_source"]}</td>
                        <td>{user["annual_income"]}</td>
                        <td>{user["account_num"]}</td>
                        <td>{user["username"]}</td>
                        <td>{user["balance"]}</td>
                        <td>{user["open_date"]}</td>
                        <td>{user["account_type"]}</td>
                        <td>{user["is_credit_card"]}</td>
                        <td>{user["is_debit_card"]}</td>
                        <td>{user["is_netbanking"]}</td>
                    </tr>
                )
            })}
        </tbody>
      </table>
    </div>
    )
}
export default AdminUsers;