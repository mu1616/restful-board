import React, { Component } from 'react';

class AddUserComponent extends Component {

    constructor(props) {
        super(props);

        this.state = {
            id : '',
            name : '',
            password : '',
            ssn: '',
            message: null
        }
    }

    onChange = (e) => {
        this.setState({
            [e.target.name] : e.target.value
        })
    }

    saveUser = (e) => {
        e.preventDefault();

        let user = {
            id : this.state.username,
            password : this.state.password,
            name : this.state.name,
            ssn : this.state.ssn
        }

        ApiService.addUser(user)
            .then(res => {
                this.setState({
                    message : user.id + '님이 성공적으로 등록되었습니다.'
                })
                console.log(this.state.message);
                this.props.history.push('/users');
            })
            .catch(err => {
                console.log('saveUser() Error!', err);
            });
    }

    render() {
        return (
            <div>
                <h2>Add User</h2>
                <form>
                    <div>
                        <label>ID:</label>
                        <input type = "text" placeholder="ID" name="id" 
                            value={this.state.id} onChange={this.onChange} />
                    </div>

                    <div>
                        <label>password:</label>
                        <input type="password" placeholder="Password" name="password"
                            value={this.state.password} onChange={this.onChange} />
                    </div>
                    
                    <div>
                        <label>Name:</label>
                        <input type="text" placeholder="Name" name="name" value={this.state.name} onChange={this.onChange} />              
                    </div>

                    <div>
                        <label>SSN:</label>
                        <input type="text" placeholder="SSN" name="ssn" value={this.state.ssn} onChange={this.onChange} />
                    </div>

                    <button onClick={this.saveUser}>Save</button>
                </form>
            </div>
        );
    }
}

export default AddUserComponent;