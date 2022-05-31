function loginApi(data) {
  return $axios({
    'url': '/agv/login',
    'method': 'post',
    'data': data
  })
}

function logoutApi(){
  return $axios({
    'url': '/employee/logout',
    'method': 'post',
  })
}
