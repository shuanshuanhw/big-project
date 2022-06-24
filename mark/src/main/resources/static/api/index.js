function getAll() {
    return $axios({
        'url': '/agv/getAll',
        'method': 'get'
    })
}

function getTopUser10() {
    return $axios({
        'url': '/getTopUser10',
        'method': 'get'
    })
}
function getTop10() {
    return $axios({
        'url': '/getTop10',
        'method': 'get'
    })
}
function getAllById(id) {
    return $axios({
        'url': `/agv/getAllById/${id}`,
        'method': 'get'
    })
}

function getAllByIds(ids) {
    return $axios({
        'url': `/agv/getAllByIds/${ids}`,
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