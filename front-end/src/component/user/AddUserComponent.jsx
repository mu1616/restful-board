import React, { Component } from 'react';
import ApiService from "../../ApiService";
import { Typography } from '@material-ui/core';

import TextField from '@material-ui/core/TextField'
import Button from '@material-ui/core/Button'

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
                <Typography variant="h4" style={style}>Add User</Typography>
                <form style={formContainer}>
                    
                    <TextField type="text" placeholder="ID" name="id" fullWidth margin="normal" value={this.state.username}
                        onChenage={this.onChange} />

                    <TextField type="Password" placeholder="Password" name="password" fullWidth margin="normal" 
                    value={this.state.password} onChenage={this.onChange} />

                    <TextField type="text" placeholder="Name" name="name" fullWidth margin="normal" 
                    value={this.state.name} onChenage={this.onChange} />
                    
                    <TextField type="text" placeholder="SSN" name="ssn" fullWidth margin="normal" 
                    value={this.state.ssn} onChenage={this.onChange} />

                    <Button variant="contained" color="primary" onClick={this.saveUser}>Save</Button>
                </form>
            </div>
        );
    }
}

const formContainer = {
    display : 'flex',
    flexFlow: 'row wrap'
}

const style = {
    display : 'flex',
    justifyContent : 'center'
}
export default AddUserComponent;