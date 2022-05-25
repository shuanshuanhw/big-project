function getAll() {
    return $axios({
        'url': '/agv/getAll',
        'method': 'get'
    })
}

function getAllById(id) {
    return $axios({
        'url': `/agv/getAllById/${id}`,
        'method': 'get'
    })
}