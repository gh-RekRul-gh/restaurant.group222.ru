<template>
<v-app class="cart_app"
        app 
        id="app"
        dark>
        <v-row>
           <v-card color="black" width="100%">
            <v-card dark color="black" class="mt-4">
                <v-row>
                <v-card class="mx-auto" max-width="80%" color="black">
                    <br><br>
                    <v-card-title class="title_order_placement text-h6 text-md-h5 text-lg-h4">Ваш заказ успешно оформлен!</v-card-title>  
                     <!--№{{order.id}}  -->
                    <v-card-text>
                        <br>
                            <v-row>
                                <v-col>
                                    <v-text class="subtitle_order_placement">На вашу электронную почту было отправлено письмо с подтверждением. </v-text>
                                </v-col>
                            </v-row>
                            
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row>
                                <v-col>
                                <v-img class="shopping_image"
                                        :src="require('../assets/status1.svg')"
                                />
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text class="subtitle_order_placement">Информация о заказе доступна в "Профиле" в разделе "Текущие заказы".</v-text>
                                </v-col>
                            </v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>
                            <v-row><v-col></v-col></v-row>  
                            <v-row>
                                        <v-col>
                                        <v-btn
                                            @click="goToHome()"
                                            class=" text-none text-subtitle"
                                            light
                                            rounded="2"
                                            width="100%"
                                            target="_blank"
                                            variant="flat"
                                            color="#D3CDBD"
                                            x-large
                                            density="compact"
                                            >Вернуться в меню</v-btn>
                                        </v-col>
                                    </v-row>
                            </v-card-text>
                        </v-card>    
            </v-row>
           </v-card>
           </v-card>
        </v-row>
</v-app>

</template>

<script>
// import axios from 'axios';

export default {    
    
    name: 'PlacedOrder_',
    
    data () {
      return {
        paymentMethods: {
             1 : "Оплата картой",
             2: "Оплата наличными"
        },
        order: 
             {
            created_at: "", //new Date(2025, 0, 17, 19, 5, 0, 0)
            status_id: "", //1
            phone_number: "", // 7777777777
            address: "", //Студенческая улица, 33к3
            courier_comment: "",
            payment_method: '', // 2
            items: [ ],
             },
      }
    },
    
    methods: {
        
        goToHome() {
            this.$router.push('/')
        },
    },
    props: ['userId']
};
</script>