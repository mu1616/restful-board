import React, { Component } from 'react';
import ApiService from "../../ApiService";

class UserListComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            users : [],
            message : null
        }
    }

    //componentDidMount()는 컴포넌트가 마운트된 직후, 즉 트리에 삽입된 직후에 호출된다.
    componentDidMount() {
        this.reloadUserList();
    }

    //state 에 있는 값을 바꾸기 위해서는, this.setState 를 무조건 거쳐야합니다. 
    //리액트에서는, 이 함수가 호출되면 컴포넌트가 리렌더링 되도록 설계되어있습니다.
    reloadUserList = () => {
        ApiService.fetchUsers()
            .then(res => {       // = function(res) { ... }
                this.setState({
                    users : res.data
                });
            })
            .catch(err => {
                console.log('reloadUserList() Error !', err);
            })
    }

    deleteUser = (userId) => {
        ApiService.deleteUser(userId)
            .then(res => {
                this.setState({
                    message : 'User Deleted Successfully.'
                });
                this.setState({        // filter -> 특정 조건에 부합되는 원소들만 뽑아서 새 배열을 만들어줌
                    users : this.state.users.filter(user =>
                        user.id !== userId)
                });
            })
            .catch(err => {
                console.log('deleteUser() Error!', err);
            })
    }

    addUser = () => {
        window.localStorage.removeItem("userId");
        this.props.history.push('/add-user');   //리다이렉트
    }
    render() {
        return (
            <div>
                <h2>User List</h2>
                <button onClick={this.addUser}>Add User</button>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Password</th>
                            <th>SSN</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.users.map( user =>
                            <tr key = {user.id}>
                                <td>{user.id}</td>
                                <td>{user.name}</td>
                                <td>{user.password}</td>
                                <td>{user.ssn}</td>
                                <td>
                                    <button onClick = { () => this.deleteUser(user.id) }>Delete</button>
                                </td>
                            </tr>
                            )}
                    </tbody>
                </table>
            </div>
        );      
    }
}

export default UserListComponent;
