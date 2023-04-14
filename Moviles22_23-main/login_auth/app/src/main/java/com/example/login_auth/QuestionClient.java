package com.example.login_auth;

public class QuestionClient {
    Qie retrofitClient = RetrofitClient.getInstance();
    QuestionService questionService = retrofitClient.getApi();

    Call<Question> call = questionService.getQuestion(questionId);
call.enqueue(new Callback<Question>() {
        @Override
        public void onResponse(Call<Question> call, Response<Question> response) {
            if (response.isSuccessful()) {
                Question question = response.body();
                // Aquí procesas la respuesta de la API
            } else {
                // Aquí manejas el error de la respuesta de la API
            }
        }

        @Override
        public void onFailure(Call<Question> call, Throwable t) {
            // Aquí manejas el error de la red
        }
    });
}
