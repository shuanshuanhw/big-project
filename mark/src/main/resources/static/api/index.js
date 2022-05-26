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

function submitFruition(dataArray) {
    return $axios({
        'url': `/agv/submitFruition`,
        'method': 'post',
        'data': dataArray
    })
}