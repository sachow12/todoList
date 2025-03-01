    package com.example.todolist.Model;

    import jakarta.persistence.*;

    import lombok.*;

    import java.time.LocalDate;
    import java.util.List;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    @Table(name = "tb_todolist")
    public class TodoList {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id ;
        @Column(name = "title")
        String title ;
        @Column(name = "description")
        String description ;
        @Column(name = "date")
        LocalDate date ;
        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "Todo", joinColumns = @JoinColumn(name = "id"))
        @Column(name = "Todo")
        List<String> Todo;
        @Column(name = "status")
        Boolean status = false;
        @Column(name = "codigo")
        Long codigo;
        @Column(name = "ativacao")
        Boolean ativacao = true;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public List<String> getTodo() {
            return Todo;
        }

        public void setTodo(List<String> todo) {
            Todo = todo;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public Long getCodigo() {
            return codigo;
        }
    }
