function getAll() {
    return $axios({
        'url': '/agv/getAll',
        'method': 'get'
    })
}