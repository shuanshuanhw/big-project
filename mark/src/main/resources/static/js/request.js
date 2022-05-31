(function (win) {
  axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
  // 创建axios实例
  const service = axios.create({
    // axios中请求配置有baseURL选项，表示请求URL公共部分
    baseURL: '/',
    // 超时
    timeout: 10000000
  })



  // request拦截器
  service.interceptors.request.use(
      config => {
    //    loading.open()
        return config
      },
      err => {
   //     loading.close()
        //       this.$router.push("/login");
        return Promise.reject(err)

      }
  )

  // 响应拦截器
  service.interceptors.response.use(
      response => {
        //拦截响应，做统一处理
        // console.log('555555')
        // console.log('response.data.code',response.data.code)

        // if (response.data.code) {
        //
        //   switch (response.data.code) {
        //     case 1002:
        //       store.state.isLogin = false
        //       router.replace({
        //         path: 'login',
        //         query: {
        //           redirect: router.currentRoute.fullPath
        //         }
        //       })
        //       console.log('333333')
        //     case 401:
        //       console.log('4444444')
        //   }
        // }

   //     loading.close()
        return response
      },
      //接口错误状态处理，也就是说无响应时的处理
      error => {
        console.log('555555')
     //   loading.close()
//        this.$router.push("/login");

        return Promise.reject(error.response.status) // 返回接口返回的错误信息
      }
  )
win.$axios = service
})(window);
