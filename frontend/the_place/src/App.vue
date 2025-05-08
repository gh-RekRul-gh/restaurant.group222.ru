<template>
  <v-app class="app">
    <v-app-bar
      app
      color="black"
      dark
    >
    <v-row>
    <v-col>
      <div class="support">
        <v-btn v-if="$route.path !== '/' "
          @click="goToHome()"
          target="_blank"
          icon
          density="compact" 
        > 
        <img
          alt="home"
          class="shrink mr-2 ml-2"
          contain
          color="#D3CDBD"
          width="40px"
        src="./assets/home5.svg"/></v-btn>
        <v-btn v-if="$route.path == '/' "
          @click="support_dialog = true"
          target="_blank"
          icon
          density="compact" 
        >
        <img
          alt="customer-care"
          class="shrink mr-2 ml-2"
          contain
          width="40px"
          src="./assets/customer-care.svg"/></v-btn>
        <v-dialog 
          v-model="support_dialog"
          width="auto"
        >
          <v-card 
            color="black"
            dark
            width="500"
          ><v-card color="black" width="100%" height="100%" class="card_support">
            <v-card-item dark>
          <v-btn width="89%" large text class="title_support text-none text-subtitle text-h6 text-md-h5 text-lg-h4 ml-5" color="#D3CDBD">Свяжитесь с нами</v-btn>
          <v-btn
              @click="support_dialog = false"
              class=" text-none text-subtitle mx-auto ma-2"
              light
              rounded="2"
              icon
              target="_blank"
              variant="flat"
              color="#D3CDBD"
              small
              density="compact"
            ><v-img color="#D3CDBD" 
                  class="ma-2" 
                  src="./assets/close.svg"/></v-btn>                
          <v-card-subtitle class="title_support ma-2">А мы постараемся поскорее ответить</v-card-subtitle>
            </v-card-item>
          <v-card-text>
            <v-card color="black" dark >
              <form class="mx-auto" color="#D3CDBD">
              <v-text-field v-model="user.name"
                  :counter="100"
                  clearable
                  :rules="[NameRules.required, NameRules.max]"
                  label="Полное имя"
                  base-color="#D3CDBD"
                  color="#D3CDBD"                                            
                  ></v-text-field>
              <v-text-field v-model="user.email"
                  label="Электронная почта"
                  type="email"
                  disabled
                  :counter="100"
                  :rules="[EmailRules.required, EmailRules.min, EmailRules.max, EmailRules.email]"
                  color="#D3CDBD"
                  base-color="#D3CDBD"
                  clearable
                  ></v-text-field>
                   <v-text-field v-model="user.support_message"
                  :counter="100"
                  :rules="[MessageRules.required, MessageRules.max]"
                  label="Сообщение"
                  color="#D3CDBD"
                  base-color="#D3CDBD"
                  clearable
                  ></v-text-field>
              </form>
              </v-card>
                      <v-btn
                      @click="support_dialog = false, send_support_message()"
                      class=" text-none text-subtitle mx-auto ma-2"
                      light
                      rounded="2"
                      width="100%"
                      target="_blank"
                      variant="flat"
                      color="#D3CDBD"
                      large
                      density="compact"
                      >Отправить</v-btn>
          </v-card-text>
          </v-card>
          </v-card>
        </v-dialog>
        </div>
      </v-col>

      <v-spacer></v-spacer>
      <v-col>
      <div id="home_title" >
        <v-btn 
          class="home_btn"
          text
          
          href="https://github.com/gh-RekRul-gh/restaurant.group222.ru"
          target="_blank"
        >
          <span      
            alt="home-title"
            class="home_title"
            contain
          >
          THE PLACE </span></v-btn>
      </div>
      </v-col>

      <v-spacer></v-spacer>
      <v-col >
      <div class="d-flex align-center">
        <v-btn v-if="authorised & $route.path == '/'"
          class="cart"
          @click="goToCart()"
          target="_blank"
          icon
          density="compact" 
        >
        <img 
          alt="shopping-cart"
          class="shrink mr-2 ml-2"
          contain
          width="40px"
          src="./assets/shopping cart.svg"/></v-btn>
        <v-btn v-if="!authorised"
          @click="login_dialog = true"
          class="enter text-none text-subtitle"
          light
          rounded='1'
          target="_blank"
          variant="flat"
          color="#D3CDBD"
          size="small"
          density="compact"
        >Вход/Регистрация</v-btn>
      </div> 
      <v-dialog v-model="login_dialog" 
                width="auto"
        >
          <v-card 
            color="black"
            dark
            width="500"
          ><v-card color="black" width="100%" height="100%" class="card_support">
             <v-card-text>
            <v-card color="black" dark >
              <v-btn v-if="!authorisation"
                        @click="authorisation = true, registration = false"
                        class=" text-none text-subtitle mx-auto ma-2 ml-5 mr-3"
                        text
                        rounded="2"
                        width="38%"
                        target="_blank"
                        variant="flat"
                        color="#D3CDBD"
                        large
                        density="compact"
                      >Авторизация</v-btn>
                <v-btn v-if="authorisation"
                        @click="authorisation = true, registration = false"
                        class=" text-none text-subtitle mx-auto ma-2 ml-5 mr-3"
                        outlined
                        rounded="2"
                        width="38%"
                        target="_blank"
                        variant="flat"
                        color="#D3CDBD"
                        large
                        density="compact"
                      >Авторизация</v-btn>
                      <v-btn v-if="!registration"
                          @click="registration = true, authorisation = false"
                          class=" text-none text-subtitle mx-auto ma-2 ml-3 mr-3"
                          text
                          rounded="2"
                          width="38%"
                          target="_blank"
                          variant="flat"
                          color="#D3CDBD"
                          large
                          density="compact"
                          >Регистрация</v-btn>
                      <v-btn v-if="registration"
                          @click="registration = true, authorisation = false"
                          class=" text-none text-subtitle mx-auto ma-2 ml-3 mr-3"
                          outlined
                          rounded="2"
                          width="38%"
                          target="_blank"
                          variant="flat"
                          color="#D3CDBD"
                          large
                          density="compact"
                          >Регистрация</v-btn>
                          <v-btn
                              @click="login_dialog = false"
                              class=" text-none text-subtitle mx-auto ma-2"
                              light
                              rounded="2"
                              icon
                              target="_blank"
                              variant="flat"
                              color="#D3CDBD"
                              small
                              density="compact"
                            ><v-img color="#D3CDBD" 
                                  class="ma-2" 
                                  src="./assets/close.svg"/></v-btn>

              <form class="mx-auto" color="#D3CDBD">
              <v-text-field  v-if="authorisation" v-model="emailEnter"
                  label="Электронная почта"
                  type="email"
                  :rules="[EmailRules.required, EmailRules.min, EmailRules.max, EmailRules.email]"
                  color="#D3CDBD"
                  base-color="#D3CDBD"
                  clearable
                  ></v-text-field>
                  <v-text-field v-if="authorisation" v-model="passwordEnter"
                      label="Пароль"
                      fast-fail
                      :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                      :type="show ? 'text' : 'password'"
                      @click:append="show = !show"
                      password
                      :rules="PaswordRules"
                      base-color="#D3CDBD"
                      color="#D3CDBD"
                    >
                </v-text-field>
              <v-text-field  v-if="registration" v-model="nameRegistration"
                  label="Полное имя"
                  type="text"
                  :rules="[NameRules.required, NameRules.max]"
                  color="#D3CDBD"
                  base-color="#D3CDBD"
                  clearable
                  ></v-text-field>
              <v-text-field  v-if="registration" v-model="emailRegistration"
                  label="Электронная почта"
                  type="email"
                  :rules="[EmailRules.required, EmailRules.min, EmailRules.max, EmailRules.email]"
                  color="#D3CDBD"
                  base-color="#D3CDBD"
                  clearable
                  ></v-text-field>
                  <v-text-field v-if="registration" v-model="passwordRegistration"
                      fast-fail
                      :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                      :type="show ? 'text' : 'password'"
                      @click:append="show = !show"
                      password
                      :rules="[PaswordRules.required, PaswordRules.min, PaswordRules.max]"
                      label="Пароль"
                      base-color="#D3CDBD"
                      color="#D3CDBD"
                    >
                </v-text-field>
              </form>
              </v-card>
                      <v-btn v-if="authorisation"
                          @click="login_dialog = false, authorised = true, login()"
                          class=" text-none text-subtitle mx-auto ma-2"
                          light
                          rounded="2"
                          width="100%"
                          target="_blank"
                          variant="flat"
                          color="#D3CDBD"
                          large
                          density="compact"
                          >Войти</v-btn>
                      <v-btn v-if="registration"
                          @click="login_dialog = false, registration = false, authorisation = true, register()"
                          class=" text-none text-subtitle mx-auto ma-2"
                          light
                          rounded="2"
                          width="100%"
                          target="_blank"
                          variant="flat"
                          color="#D3CDBD"
                          large
                          density="compact"
                          >Зарегистрироваться</v-btn>
                       <v-btn v-if="authorisation"
                          @click="login_dialog = false, password_recover_dialog = true"
                          class=" text-none text-subtitle mx-auto ma-2"
                          rounded="2"
                          width="100%"
                          target="_blank"
                          text
                          color="#D3CDBD"
                          small
                          density="compact"
                      >Забыли пароль?</v-btn>
                      <v-dialog v-model="password_recover_dialog"  width="auto"
                              >
                                <v-card 
                                  color="black"
                                  dark
                                  width="500"
                                ><v-card color="black" width="100%" height="100%" class="card_support">
                                  <v-card color="black" dark >
                                    <v-card-title class="title_support ma-2"> 
                                      <v-spacer></v-spacer>
                             <v-btn
                                @click="password_recover_dialog = false"
                                class="text-none text-subtitle mx-left ma-2"
                                light
                                rounded="2"
                                icon
                                target="_blank"
                                variant="flat"
                                color="#D3CDBD"
                                small
                                density="compact"
                              ><v-img color="#D3CDBD" 
                                    class="ma-2" 
                                    src="./assets/close.svg"/></v-btn>   
                                    </v-card-title>
                                    <v-card-subtitle class="title_support ma-2" color="#D3CDBD"> Восстановление пароля </v-card-subtitle>  
                                  </v-card>             
                            <v-card-subtitle x-small class="subtitle_support ma-2">Для восстановления пароля введите почту, которую Вы указывали при регистрации. <br>
На нее будет отправлен код, который будет необходимо ввести далее.</v-card-subtitle>
                              <!-- </v-card> -->
                              <v-card-text>
                        <form class="mx-auto" color="#D3CDBD">
                          <v-text-field v-model="emailRecover"
                              label="Электронная почта"
                              type="email"
                              :rules="[EmailRules.required, EmailRules.min, EmailRules.max, EmailRules.email]"
                              color="#D3CDBD"
                              base-color="#D3CDBD"
                              clearable
                              ></v-text-field>
                        </form>
                        <v-btn
                          @click="password_recover_dialog = false, password_recover2_dialog = true"
                          class=" text-none text-subtitle mx-auto ma-2"
                          light
                          rounded="2"
                          width="100%"
                          target="_blank"
                          variant="flat"
                          color="#D3CDBD"
                          large
                          density="compact"
                          >Отправить код</v-btn>
                          <v-dialog v-model="password_recover2_dialog" width="auto"
                              >
                                <v-card 
                                  color="black"
                                  dark
                                  width="500"
                                ><v-card color="black" width="100%" height="100%" class="card_support">
                                  <v-card color="black" dark >
                                    <v-card-title class="title_support ma-2"> 
                                      <v-spacer></v-spacer>
                             <v-btn
                                @click="password_recover2_dialog = false"
                                class="text-none text-subtitle mx-left ma-2"
                                light
                                rounded="2"
                                icon
                                target="_blank"
                                variant="flat"
                                color="#D3CDBD"
                                small
                                density="compact"
                              ><v-img color="#D3CDBD" 
                                    class="ma-2" 
                                    src="./assets/close.svg"/></v-btn>   
                                    </v-card-title>
                                    <v-card-subtitle class="title_support ma-2" color="#D3CDBD"> Восстановление пароля </v-card-subtitle>  
                                  </v-card>             
                              <v-card-text>
                        <form class="mx-auto" color="#D3CDBD">
                              <v-text>Введите код, отправленный Вам на почту:</v-text>
                              <v-otp-input v-model="otp"
                                class="mb-8"
                                variant="outlined"
                                :loading="loading"
                                color="#D3CDBD"
                                length="6"
                              ></v-otp-input>
                          <!-- <v-text-field label="Код, отправленный на почту"
                              type="email"
                              :rules="EmailRules"
                              color="#D3CDBD"
                              base-color="#D3CDBD"
                              clearable
                              ></v-text-field> -->
                              <v-text-field v-model="newPasswordFirst"
                                    fast-fail
                                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                                    :type="show1 ? 'text' : 'password'"
                                    @click:append="show1 = !show1"
                                    password
                                    :rules="[PaswordRules.required, PaswordRules.min, PaswordRules.max]"
                                    label="Новый пароль"
                                    base-color="#D3CDBD"
                                    color="#D3CDBD"
                                  >
                              </v-text-field>
                              <v-text-field v-model="newPasswordSecond"
                                    fast-fail
                                    :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                                    :type="show2 ? 'text' : 'password'"
                                    @click:append="show2 = !show2"
                                    password
                                    :rules="[PaswordRules.required, PaswordRules.min, PaswordRules.max, passwordConfirmationRule()]"
                                    label="Подтвердите новый пароль"
                                    base-color="#D3CDBD"
                                    color="#D3CDBD"
                                  >
                              </v-text-field>
                        </form>
                        <v-btn v-if="otp.length < 6 || loading"
                          @click="onClick()"
                          class=" text-none text-subtitle mx-auto ma-2"
                          dark
                          rounded="2"
                          width="100%"
                          target="_blank"
                          variant="flat"
                          color="#D3CDBD"
                          large
                          disabled
                          density="compact"
                          >Восстановить пароль</v-btn>
                          <v-btn v-else
                            @click="onClick()"
                            class=" text-none text-subtitle mx-auto ma-2"
                            light
                            rounded="2"
                            width="100%"
                            target="_blank"
                            variant="flat"
                            color="#D3CDBD"
                            large
                            density="compact"
                            >Восстановить пароль</v-btn>
                          </v-card-text>
                        </v-card>
                        </v-card>
                      </v-dialog>
                          </v-card-text>
                        </v-card>
                        </v-card>
                      </v-dialog>
          </v-card-text>
          </v-card>
          </v-card>
      </v-dialog>
      </v-col>
      <v-col>
      <div class="d-flex align-center">
        <v-btn v-if="authorised & ($route.path == '/cart/' |  $route.path == '/')"
          class="profile"
          @click="goToProfile()"
          target="_blank"
          icon
          density="compact" 
        >
      <img 
          alt="profile"
          class="shrink mr-2 ml-2"
          contain
          width="40px"
          src="./assets/profile.svg"/> </v-btn> 
      </div>
      </v-col>
      </v-row>   
    </v-app-bar>
    
    <v-main class="main">
      <router-view></router-view>
      <!-- <Main v-if="$route.path == '/'"/>
      <Profile v-if="$route.path == '/profile/'"/>
      <ShoppingCart v-if="$route.path == '/cart/'"/> -->
    </v-main>

    <v-footer border class="footer ga-2 py-3" color="black" >
        <div class="d-flex align-center">
          <img
            alt="customer-care"
            class="shrink mr-2"
            contain
            width="20px"
          src="./assets/telephone.svg"/>
        <strong contain class="footer_number_mail">+7(777) 777-77-77</strong>
        </div>    

        <v-spacer></v-spacer>

        <div id="home_title">
          <v-btn 
            text
            href="https://github.com/gh-RekRul-gh/restaurant.group222.ru"
            target="_blank"
          >
            <span      
              alt="home_title_footer"
              class="home_title_footer"
              contain
            > THE PLACE </span> </v-btn>

        </div>

        <v-spacer></v-spacer>

           <strong contain class="footer_number_mail" >theplace@place.com</strong>
        <div class="d-flex align-center">
          <img
            alt="letter"
            class="shrink ml-2 mt-2"
            contain
            
            width="20px"
          src="./assets/letter.svg"/>
        </div>

        <div class="footer_data">
        {{ new Date().getFullYear() }} - GROUP222
        </div>

     
      
    </v-footer>
  </v-app>
</template>

<script>
// import Main from './views/Main';
// import Profile from './views/Profile';
// import ShoppingCart from './views/ShoppingCart';
// import { required, minLength, sameAs, maxLength, alpha, integer, email } from 'vuelidate/lib/validators'
import axios from 'axios';

export default {
  
  name: 'App',

  components: {
    // Main,
    // Profile,
    // ShoppingCart
  },

  data() {
    return {
        emailEnter: '',
        passwordEnter: '',
        emailRecover: '',
        userId: '',
        nameRegistration: '',
        emailRegistration: '',
        passwordRegistration: '',
        
        newPasswordFirst: '',
        newPasswordSecond: '',
        otp: '', // код для восстановления пароля
        user: {
            name: "Иван Иванов",
            support_message: "",
            email: "ivanivanov@gmail.com",
        },
        loading: false,
        password_recover_dialog: false,
        password_recover2_dialog: false,
        authorisation: true,
        registration: false,
        show: false,
        show1:false,
        show2:false,
        support_dialog: false,
        login_dialog: false,
        authorised: false, // true
        PaswordRules: { //!!!
          required: value => {!!value || 'Необходимо заполнить'}, //Required.
          min: v => v.length >= 6 || 'Минимум 6 символов', //Min 6 characters
          max: v => v.length <= 100 || 'Не более 100 символов', //Max 100 characters
          emailMatch: () => (`The email and password you entered don't match`),
        },
        EmailRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Required.
          min: v => v.length >= 8 || 'Минимум 8 символов', //Min 8 characters
          max: v => v.length <= 100 || 'Не более 100 символов', //Max 100 characters
          email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Неверный адрес электронной почты' // Invalid e-mail.
          },
        },
        NameRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Required.
          // min: v => v.length >= 4 || 'Минимум 4 символа', //Min 4 characters
          max: v => v.length <= 100 || 'Не более 100 символов', //Max 100 characters
        }, 
        MessageRules: { //!!!
          required: value => !!value || 'Необходимо заполнить', //Required.
          max: v => v.length <= 100 || 'Не более 100 символов', //Max 100 characters
        },
    }
  },
  
  methods: {
    send_support_message() {
      // let user;
      //       user.push({
      //           name: this.user.name,
      //           email: this.user.email,
      //           message: this.user.support_message,
      //       });
            try {
            axios.post('http://localhost:8080/api/v1/user/support', {
                name: this.user.name,
                email: this.user.email,
                message: this.user.support_message,
            })
            // .then((response) => {
            //     let id = response.data
            //     this.id = id
            //         // this.$router.push('/login');
            // })
            } catch(error) {
              console.log(error);
            }
    },
     register(){
            // this.$axios.get('http://localhost:8080/api/v1/user/register')
            //     .then((response) => {
            //         let id = response.data
            //         let user = response.data
            //         user.push({
            //             name: this.nameRegistration,
            //             email: this.emailRegistration,
            //             password: this.passwordRegistration,
            //         })
            // let user;
            // user.push({
            //     name: this.nameRegistration,
            //     email: this.emailRegistration,
            //     password: this.passwordRegistration,
            // });
            try {
            axios.post('http://localhost:8080/api/v1/user/register', {
                name: this.nameRegistration,
                email: this.emailRegistration,
                password: this.passwordRegistration,
            })
            .then((response) => {
                let id = response.data
                this.userId = id
            })
            } catch(error) {
              console.log(error);
            }
        },
      login(){
        // let user;
        //     user.push({
        //         email: this.emailEnter,
        //         password: this.passwordEnter,
        //     });
        try {
            axios.post('http://localhost:8080/api/v1/user/login', {
                email: this.emailEnter,
                password: this.passwordEnter,
            })
            .then((response) => {
                let id = response.data
                this.userId = id
                    // this.$router.push('/login');
            })
            } catch(error) {
              console.log(error);
            }
      },
      password_recovery(){
        try {
            axios.post('http://localhost:8080/api/v1/user/password-recovery', {
                email: this.emailRecover,
            })
            // .then((response) => {
            //     let data = response.data
            //     console.log(data);
            // })
            } catch(error) {
              console.log(error);
            }
      },

      onClick () {
        this.loading = true

        setTimeout(() => {
          this.loading = false
        }, 2000)
      },
    goToHome() {
      const userId = this.userId
      if (userId != null)
        this.$router.push(`/user/${userId}`)
      else 
        this.$router.push(`/user`)
    },
    goToProfile() {
      const userId = this.userId
      this.$router.push(`/profile/${userId}`)
    },
    goToCart() {
      const userId = this.userId
      this.$router.push(`/cart/${userId}`)
    },
  },
      computed: {
          passwordConfirmationRule() {
            return () => (this.newPasswordFirst === this.newPasswordSecond) || 'Пароли не совпадают' // Passwords must match
          }
      },
   
    // props:['userId'],
    // setup(props) {
    // console.log(props.userId)
  // }
}


</script>

<script setup>
//  import { useTheme } from 'vuetify'


//  const theme = useTheme()

//  function toggleTheme() {
//    theme.global.name.value = theme.global.current.value.dark ? 'light' : 'dark'
//  }
//  </script>

<style lang="scss">
.app {
  font-family: "Lexend Exa", sans-serif;
  // transition: background-color 0.3s ease;  
  width: 100%;
  justify-content: center; 
  flex: wrap;
  flex-direction: row;
  display: flex;
  flex-direction: row;
  // background: #000 url("../assets/wallpaper.png") no-repeat fixed left bottom;
}
.app.light-theme {
    background-color: #D3CDBD;
    color: #000;
}
.app.dark-theme {
    background-color: #000;
    color: #D3CDBD;
}
.main {
  background-image: url("./assets/wallpaper.png");
  background-size: 100%;
}
.home_btn {
  text-align: center;
   position: center;
   margin-left: 50%
}
.home_title {
    font-family: "Lexend Exa", sans-serif;
    text-align: center;
    position: center;
    align-self: center;
    display: inline-flex;
    flex-grow: 1;
    // margin-left: 100%; //180px
    font-size: 40px;
    letter-spacing: 4px;
    color: #D3CDBD;
}

.cart {
  text-align: center;
  display: inline-flex;
  margin-left: 145%;
}
.profile {
  text-align: center;
  display: inline-flex;
  margin-left: 75%;
}
.enter {
  font-family: "Lexend Exa", sans-serif;
  text-align: center;
  display: inline-flex;
  flex-grow: 2;
  line-height: 100%;
  // border-radius: 15px;
  align-self: center;
  letter-spacing: 2px;
  margin-left: 300px;
  margin-right: -270px;
  border-color: #D3CDBD;
}
.support {
  display: inline-flex;
  flex-grow: 0;
}
.home_title_footer {
    font-family: "Lexend Exa", sans-serif;
    text-align: center;
    position: center;
    display: inline-flex;
    font-size: 30px;
    align-self: center;
    letter-spacing: 4px;
    // text-align: center;
    // position: center;
    color: #D3CDBD;
}
.footer {
  width: 100%;
  text-align: center; 
  justify-content: center; 
  flex: wrap;
  flex-direction: row;
  display: flex;
  flex-direction: row;
}
.footer_number_mail {
    font-family: "Lexend Exa", sans-serif;
    display: inline-flex;
    font-size: 12px;
    flex-grow: 0;
    line-height: 100%;
    letter-spacing: 4px;
    color: #D3CDBD;
}
.footer_data {
  font-family: "Lexend Exa", sans-serif;
  flex: 1 0 100%; 
  margin-top: 7px;
  font-size: 12px;
  text-align: center;
  position: center;
  color: #D3CDBD;
}
.subtitle_support {
    font-family: "Lexend Exa", sans-serif;
    color: #D3CDBD;
    letter-spacing: 2px;
    text-align: center;
}
.title_support {
    font-family: "Lexend Exa", sans-serif;
    color: #D3CDBD;
    letter-spacing: 2px;
    font-weight: bold;
    text-align: center;
}
</style>


