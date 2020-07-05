import axios from 'axios';

class ApiService {

    fetchUsers() {
        return axios.get('/users');
    }

    fetchUserById(userId) {
        return axios.get('/users' + userId);
    }

    deleteUser(userId) {
        return axios.delete('/users/' + userId);
    }

    addUser(user) {
        return axios.post('/users', user);
    }
}

export default new ApiService();