SET SESSION sql_safe_updates = 0;

DELETE
FROM answer;
DELETE
FROM options;
DELETE
FROM user_question;
DELETE
FROM user_topic;
DELETE
FROM question;
DELETE
FROM topic;
DELETE
FROM learning_module;
DELETE
FROM users;


-- Insert ke tabel learning_module
SET @learning_module_id = uuid_to_bin(uuid());
INSERT INTO learning_module (id, name, description, method)
VALUES (@learning_module_id, 'Interaksi Sosial', '', '');

-- Insert ke tabel topic
SET @topic_id = uuid_to_bin(uuid());
INSERT INTO topic (id, name, description, method, level, learning_module_id)
VALUES (@topic_id, 'Mencuci Tangan', 'Belajar mencuci tangan', 'Mencocokan Gambar', 0, @learning_module_id);

-- Insert ke tabel question
SET @question_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (
           @question_id,
           @topic_id,
           'video',
           '/uploads/videos/mencuci_tangan.mp4',
           'Mencuci tangan merupakan salah satu kegiatan penting yang dilakukan untuk menjaga kebersihan dan sanitasi diri, khususnya sebelum makan.\n\nBerikut enam langkah dalam mencuci tangan yang baik dan benar:\n1. Membasahi tangan\n2. Menuang sabun\n3. Menggosok sela jari\n4. Menggosok punggung tangan\n5. Menggosok kuku\n6. Membilas tangan yang sudah dicuci\n7. Mengeringkan tangan dengan lap atau tisu kering\n\nTeman-teman jangan lupa untuk mencuci tangan sebelum makan ya!',
           NULL,
           0
       );

-- Insert pertanyaan pertama
SET @question2_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question2_id, @topic_id, 'image', '/uploads/images/membasahi_tangan.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan pertama
SET @option1_q2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_q2, @question2_id, 'Membasahi Tangan');

SET @option2_q2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_q2, @question2_id, 'Menggosok Kuku');

SET @option3_q2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_q2, @question2_id, 'Menuang Sabun');

SET @option4_q2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_q2, @question2_id, 'Menggosok Sela Jari');

-- Insert jawaban benar untuk pertanyaan pertama
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question2_id, @option2_q2);

-- Insert pertanyaan kedua
SET @question3_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question3_id, @topic_id, 'image', '/uploads/images/menuang_sabun.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan kedua
SET @option1_q3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_q3, @question3_id, 'Mengelap Tangan');

SET @option2_q3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_q3, @question3_id, 'Menuang Sabun');

SET @option3_q3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_q3, @question3_id, 'Menggosok Kuku');

SET @option4_q3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_q3, @question3_id, 'Membilas Tangan');

-- Insert jawaban benar untuk pertanyaan kedua
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question3_id, @option3_q3);

-- Insert pertanyaan ketiga
SET @question4_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question4_id, @topic_id, 'image', '/uploads/images/Menggosok_Sela_Jari.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan ketiga
SET @option1_q4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_q4, @question4_id, 'Membilas Tangan');

SET @option2_q4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_q4, @question4_id, 'Menuang Sabun');

SET @option3_q4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_q4, @question4_id, 'Menggosok Sela Jari');

SET @option4_q4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_q4, @question4_id, 'Menggosok Punggung Tangan');

-- Insert jawaban benar untuk pertanyaan ketiga
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question4_id, @option3_q4);

-- Insert pertanyaan keempat
SET @question5_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question5_id, @topic_id, 'image', '/uploads/images/Menggosok_Punggung_Tangan.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan keempat
SET @option1_q5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_q5, @question5_id, 'Membilas Tangan');

SET @option2_q5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_q5, @question5_id, 'Menuang Sabun');

SET @option3_q5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_q5, @question5_id, 'Menggosok Sela Jari');

SET @option4_q5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_q5, @question5_id, 'Menggosok Punggung Tangan');

-- Insert jawaban benar untuk pertanyaan keempat
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question5_id, @option4_q5);

-- Insert pertanyaan kelima
SET @question6_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question6_id, @topic_id, 'image', '/uploads/images/Menggosok_Kuku.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan kelima
SET @option1_q6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_q6, @question6_id, 'Menggosok Kuku');

SET @option2_q6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_q6, @question6_id, 'Membasahi Tangan');

SET @option3_q6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_q6, @question6_id, 'Mengelap Tangan');

SET @option4_q6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_q6, @question6_id, 'Menggosok Punggung Tangan');

-- Insert jawaban benar untuk pertanyaan kelima
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question6_id, @option1_q6);

-- Insert pertanyaan keenam
SET @question7_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question7_id, @topic_id, 'image', '/uploads/images/Mengelap_Tangan.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan keenam
SET @option1_q7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_q7, @question7_id, 'Menuang Sabun');

SET @option2_q7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_q7, @question7_id, 'Menggosok Sela Jari');

SET @option3_q7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_q7, @question7_id, 'Menggosok Kuku');

SET @option4_q7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_q7, @question7_id, 'Mengelap Tangan');

-- Insert jawaban benar untuk pertanyaan keenam
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question7_id, @option4_q7);

-- Insert topik baru "Cara Berkomunikasi"
SET @topic_communication_id = uuid_to_bin(uuid());
INSERT INTO topic (id, name, description, method, level, learning_module_id)
VALUES (@topic_communication_id, 'Cara Berkomunikasi', 'Belajar cara berkomunikasi', 'Mencocokan Gambar', 1, @learning_module_id);

-- Insert pertanyaan pertama dalam bentuk video
SET @question_communication1_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (
           @question_communication1_id,
           @topic_communication_id,
           'video',
           '/uploads/videos/cara_berkomunikasi.mp4',
           'Berkomunikasi sangat penting untuk membantu kita dalam bersosialisasi dengan orang yang kita kenal.\n\nEmpat cara berkomunikasi:\n1. Menyapa\n2. Meminta tolong\n3. Minta maaf\n4. Berterima kasih\n\nTerapkan empat cara di atas dalam kehidupan sehari-hari ya!',
           NULL,
           0
       );

-- Insert pertanyaan pertama (Menyapa)
SET @question_communication2_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_communication2_id, @topic_communication_id, 'image', '/uploads/images/Menyapa.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Menyapa"
SET @option1_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc2, @question_communication2_id, 'Menyapa');

SET @option2_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc2, @question_communication2_id, 'Minta Tolong');

SET @option3_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc2, @question_communication2_id, 'Berterima Kasih');

SET @option4_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc2, @question_communication2_id, 'Minta Maaf');

-- Insert jawaban benar untuk pertanyaan "Menyapa"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_communication2_id, @option2_qc2);

-- Insert pertanyaan kedua (Berterima Kasih)
SET @question_communication3_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_communication3_id, @topic_communication_id, 'image', '/uploads/images/Berterima_Kasih.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Berterima Kasih"
SET @option1_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc3, @question_communication3_id, 'Minta Tolong');

SET @option2_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc3, @question_communication3_id, 'Minta Maaf');

SET @option3_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc3, @question_communication3_id, 'Berterima Kasih');

SET @option4_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc3, @question_communication3_id, 'Menyapa');

-- Insert jawaban benar untuk pertanyaan "Berterima Kasih"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_communication3_id, @option3_qc3);

-- Insert pertanyaan ketiga (Minta Tolong)
SET @question_communication4_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_communication4_id, @topic_communication_id, 'image', '/uploads/images/Minta_Tolong.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Minta Tolong"
SET @option1_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc4, @question_communication4_id, 'Minta Maaf');

SET @option2_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc4, @question_communication4_id, 'Minta Tolong');

SET @option3_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc4, @question_communication4_id, 'Berterima Kasih');

SET @option4_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc4, @question_communication4_id, 'Menyapa');

-- Insert jawaban benar untuk pertanyaan "Minta Tolong"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_communication4_id, @option2_qc4);

-- Insert pertanyaan keempat (Minta Maaf)
SET @question_communication5_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_communication5_id, @topic_communication_id, 'image', '/uploads/images/Minta_Maaf.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Minta Maaf"
SET @option1_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc5, @question_communication5_id, 'Minta Maaf');

SET @option2_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc5, @question_communication5_id, 'Minta Tolong');

SET @option3_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc5, @question_communication5_id, 'Berterima Kasih');

SET @option4_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc5, @question_communication5_id, 'Menyapa');

-- Insert jawaban benar untuk pertanyaan "Minta Maaf"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_communication5_id, @option1_qc5);

-- Insert topik baru "Mengenal Ekspresi"
SET @topic_expression_id = uuid_to_bin(uuid());
INSERT INTO topic (id, name, description, method, level, learning_module_id)
VALUES (@topic_expression_id, 'Mengenal Ekspresi', 'Belajar mengenal ekspresi', 'Mencocokan Gambar', 2, @learning_module_id);

-- Insert pertanyaan pertama dalam bentuk video
SET @question_expression1_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (
           @question_expression1_id,
           @topic_expression_id,
           'video',
           '/uploads/videos/mengenal_ekspresi.mp4',
           'Sebagai manusia, kita mempunyai emosi untuk mengekspresikan akan suatu hal yang terjadi\n\nEmpat jenis emosi:\n1. Senang\n2. Sedih\n3. Marah\n4. Takut\n\nKenali emosi di diri teman-teman ya!',
           NULL,
           0
       );

-- Insert pertanyaan pertama (Marah)
SET @question_expression2_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_expression2_id, @topic_expression_id, 'image', '/uploads/images/marah.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Marah"
SET @option1_qe2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qe2, @question_expression2_id, 'Senang');

SET @option2_qe2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qe2, @question_expression2_id, 'Sedih');

SET @option3_qe2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qe2, @question_expression2_id, 'Marah');

SET @option4_qe2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qe2, @question_expression2_id, 'Takut');

-- Insert jawaban benar untuk pertanyaan "Marah"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_expression2_id, @option4_qe2);

-- Insert pertanyaan kedua (Berterima Kasih)
SET @question_expression3_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_expression3_id, @topic_expression_id, 'image', '/uploads/images/Berterima_Kasih.png', NULL, FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Berterima Kasih"
SET @option1_qe3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qe3, @question_expression3_id, 'Sedih');

SET @option2_qe3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qe3, @question_expression3_id, 'Takut');

SET @option3_qe3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qe3, @question_expression3_id, 'Senang');

SET @option4_qe3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qe3, @question_expression3_id, 'Marah');

-- Insert jawaban benar untuk pertanyaan "Berterima Kasih"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_expression3_id, @option4_qe3);

-- Insert learning module baru "Akademik"
SET @learning_module_academic_id = uuid_to_bin(uuid());
INSERT INTO learning_module (id, name, description, method)
VALUES (@learning_module_academic_id, 'Akademik', '', '');

-- Insert topik pertama "Mengenal Bangun Datar"
SET @topic_shapes_id = uuid_to_bin(uuid());
INSERT INTO topic (id, name, description, method, level, learning_module_id)
VALUES (@topic_shapes_id, 'Mengenal Bangun Datar', 'Belajar mengenal bangun datar', 'Mencocokan Gambar', 0, @learning_module_academic_id);

-- Insert pertanyaan pertama (Persegi)
SET @question_shapes1_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_shapes1_id, @topic_shapes_id, 'image', '/uploads/images/persegi.png', 'Bangun datar di atas adalah', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Bangun Datar - Persegi"
SET @option1_qs1 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qs1, @question_shapes1_id, 'PERSEGI');

-- Insert jawaban benar untuk pertanyaan "Bangun Datar - Persegi"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes1_id, @option1_qs1);

-- Insert pertanyaan kedua (Persegi Panjang)
SET @question_shapes2_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_shapes2_id, @topic_shapes_id, 'image', '/uploads/images/persegi_panjang.png', 'Bangun datar di atas adalah', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Persegi Panjang"
SET @option1_qs2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qs2, @question_shapes2_id, 'PERSEGI PANJANG');

-- Insert jawaban benar untuk pertanyaan "Persegi Panjang"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes2_id, @option1_qs2);

-- Insert pertanyaan ketiga (Lingkaran)
SET @question_shapes3_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_shapes3_id, @topic_shapes_id, 'image', '/uploads/images/lingkaran.png', 'Bangun datar di atas adalah', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Lingkaran"
SET @option1_qs3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qs3, @question_shapes3_id, 'LINGKARAN');

-- Insert jawaban benar untuk pertanyaan "Lingkaran"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes3_id, @option1_qs3);

-- Insert pertanyaan keempat (Segitiga)
SET @question_shapes4_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_shapes4_id, @topic_shapes_id, 'image', '/uploads/images/segitiga.png', 'Bangun datar di atas adalah', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Segitiga"
SET @option1_qs4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qs4, @question_shapes4_id, 'SEGITIGA');

-- Insert jawaban benar untuk pertanyaan "Segitiga"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes4_id, @option1_qs4);

-- Insert pertanyaan kelima (Layang-Layang)
SET @question_shapes5_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_shapes5_id, @topic_shapes_id, 'image', '/uploads/images/layang_layang.png', 'Bangun datar di atas adalah', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Layang-Layang"
SET @option1_qs5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qs5, @question_shapes5_id, 'LAYANG-LAYANG');

-- Insert jawaban benar untuk pertanyaan "Layang-Layang"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes5_id, @option1_qs5);

-- Insert pertanyaan keenam (Layang-Layang Specific)
SET @question_shapes6_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_shapes6_id, @topic_shapes_id, 'image', '/uploads/images/layang_layang_specific.png', 'Bangun datar ini memiliki dua pasang sisi sama panjang yang saling berdekatan. Apakah nama bangun datar ini?', FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Layang-Layang Specific"
SET @option1_qs6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qs6, @question_shapes6_id, 'LAYANG-LAYANG');

-- Insert jawaban benar untuk pertanyaan "Layang-Layang Specific"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes6_id, @option1_qs6);

-- Insert pertanyaan ketujuh (Multiple Shapes)
SET @question_shapes7_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_shapes7_id, @topic_shapes_id, 'image', '/uploads/images/multiple_shapes.png', 'Bangun datar berikut terdiri dari beberapa bentuk. Pilih semua yang sesuai.', TRUE, 1);

-- Insert opsi jawaban untuk pertanyaan "Multiple Shapes"
SET @option1_qs7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qs7, @question_shapes7_id, 'SEGITIGA');

SET @option2_qs7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qs7, @question_shapes7_id, 'PERSEGI');

SET @option3_qs7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qs7, @question_shapes7_id, 'LINGKARAN');

SET @option4_qs7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qs7, @question_shapes7_id, 'PERSEGI PANJANG');

-- Insert jawaban benar untuk pertanyaan "Multiple Shapes" (Jawaban lebih dari satu)
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes7_id, @option1_qs7);
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_shapes7_id, @option2_qs7);

-- Insert topik baru "Mengenal Angka"
SET @topic_numbers_id = uuid_to_bin(uuid());
INSERT INTO topic (id, name, description, method, level, learning_module_id)
VALUES (@topic_numbers_id, 'Mengenal Angka', 'Belajar mengenal angka', 'Mencocokan Gambar', 1, @learning_module_academic_id);

-- Insert pertanyaan pertama (Angka 1)
SET @question_numbers1_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers1_id, @topic_numbers_id, 'image', '/uploads/images/angka_1.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 1"
SET @option1_qn1 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn1, @question_numbers1_id, 'SATU');

-- Insert jawaban benar untuk pertanyaan "Angka 1"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers1_id, @option1_qn1);

-- Insert pertanyaan kedua (Angka 2)
SET @question_numbers2_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers2_id, @topic_numbers_id, 'image', '/uploads/images/angka_2.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 2"
SET @option1_qn2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn2, @question_numbers2_id, 'DUA');

-- Insert jawaban benar untuk pertanyaan "Angka 2"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers2_id, @option1_qn2);

-- Insert pertanyaan ketiga (Angka 3)
SET @question_numbers3_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers3_id, @topic_numbers_id, 'image', '/uploads/images/angka_3.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 3"
SET @option1_qn3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn3, @question_numbers3_id, 'TIGA');

-- Insert jawaban benar untuk pertanyaan "Angka 3"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers3_id, @option1_qn3);

-- Insert pertanyaan keempat (Angka 4)
SET @question_numbers4_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers4_id, @topic_numbers_id, 'image', '/uploads/images/angka_4.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 4"
SET @option1_qn4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn4, @question_numbers4_id, 'EMPAT');

-- Insert jawaban benar untuk pertanyaan "Angka 4"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers4_id, @option1_qn4);

-- Insert pertanyaan kelima (Angka 5)
SET @question_numbers5_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers5_id, @topic_numbers_id, 'image', '/uploads/images/angka_5.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 5"
SET @option1_qn5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn5, @question_numbers5_id, 'LIMA');

-- Insert jawaban benar untuk pertanyaan "Angka 5"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers5_id, @option1_qn5);

-- Insert pertanyaan keenam (Angka 6)
SET @question_numbers6_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers6_id, @topic_numbers_id, 'image', '/uploads/images/angka_6.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 6"
SET @option1_qn6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn6, @question_numbers6_id, 'ENAM');

-- Insert jawaban benar untuk pertanyaan "Angka 6"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers6_id, @option1_qn6);

-- Insert pertanyaan ketujuh (Angka 7)
SET @question_numbers7_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers7_id, @topic_numbers_id, 'image', '/uploads/images/angka_7.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 7"
SET @option1_qn7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn7, @question_numbers7_id, 'TUJUH');

-- Insert jawaban benar untuk pertanyaan "Angka 7"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers7_id, @option1_qn7);

-- Insert pertanyaan kedelapan (Angka 8)
SET @question_numbers8_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers8_id, @topic_numbers_id, 'image', '/uploads/images/angka_8.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 8"
SET @option1_qn8 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn8, @question_numbers8_id, 'DELAPAN');

-- Insert jawaban benar untuk pertanyaan "Angka 8"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers8_id, @option1_qn8);

-- Insert pertanyaan kesembilan (Angka 9)
SET @question_numbers9_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers9_id, @topic_numbers_id, 'image', '/uploads/images/angka_9.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 9"
SET @option1_qn9 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn9, @question_numbers9_id, 'SEMBILAN');

-- Insert jawaban benar untuk pertanyaan "Angka 9"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers9_id, @option1_qn9);

-- Insert pertanyaan kesepuluh (Angka 0)
SET @question_numbers10_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers10_id, @topic_numbers_id, 'image', '/uploads/images/angka_0.png', 'Angka ini disebut?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Angka 0"
SET @option1_qn10 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn10, @question_numbers10_id, 'NOL');

-- Insert jawaban benar untuk pertanyaan "Angka 0"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers10_id, @option1_qn10);

-- Insert pertanyaan kesebelas (Pilih angka 2)
SET @question_numbers11_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers11_id, @topic_numbers_id, 'image', '/uploads/images/angka_2.png', 'Pilih nama yang sesuai untuk angka ini.', FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Pilih angka 2"
SET @option1_qn11 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn11, @question_numbers11_id, 'SATU');

SET @option2_qn11 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qn11, @question_numbers11_id, 'DUA');

SET @option3_qn11 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qn11, @question_numbers11_id, 'TIGA');

SET @option4_qn11 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qn11, @question_numbers11_id, 'NOL');

-- Insert jawaban benar untuk pertanyaan "Pilih angka 2"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers11_id, @option2_qn11);

-- Insert pertanyaan kedua belas (Pilih angka 5)
SET @question_numbers12_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers12_id, @topic_numbers_id, 'image', '/uploads/images/angka_5.png', 'Pilih nama yang sesuai untuk angka ini.', FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Pilih angka 5"
SET @option1_qn12 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn12, @question_numbers12_id, 'EMPAT');

SET @option2_qn12 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qn12, @question_numbers12_id, 'LIMA');

SET @option3_qn12 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qn12, @question_numbers12_id, 'TUJUH');

SET @option4_qn12 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qn12, @question_numbers12_id, 'NOL');

-- Insert jawaban benar untuk pertanyaan "Pilih angka 5"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers12_id, @option2_qn12);

-- Insert pertanyaan ketiga belas (Multiple Choice untuk Angka 6)
SET @question_numbers13_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers13_id, @topic_numbers_id, 'image', '/uploads/images/angka_6.png', 'Pilih nama yang sesuai untuk angka ini.', TRUE, 1);

-- Insert opsi jawaban untuk pertanyaan "Multiple Choice untuk Angka 6"
SET @option1_qn13 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qn13, @question_numbers13_id, 'ENAM');

SET @option2_qn13 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qn13, @question_numbers13_id, 'LIMA');

SET @option3_qn13 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qn13, @question_numbers13_id, 'NOL');

SET @option4_qn13 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qn13, @question_numbers13_id, 'SATU');

-- Insert jawaban benar untuk pertanyaan "Multiple Choice untuk Angka 6"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers13_id, @option1_qn13);
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers13_id, @option2_qn13);

-- Insert pertanyaan keempat belas (Multiple Choice untuk kombinasi angka 9 dan 0)
SET @question_numbers14_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_numbers14_id, @topic_numbers_id, 'image', '/uploads/images/angka_9_0.png', 'Gambar ini memiliki angka kombinasi. Pilih semua angka yang sesuai.', TRUE, 1);

-- Insert jawaban benar untuk pertanyaan kombinasi angka 9 dan 0
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers14_id, @option2_qn11);
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_numbers14_id, @option4_qn11);

-- Insert topik baru "Mencocokan Jumlah Benda"
SET @topic_counting_id = uuid_to_bin(uuid());
INSERT INTO topic (id, name, description, method, level, learning_module_id)
VALUES (@topic_counting_id, 'Mencocokan Jumlah Benda', 'Belajar mencocokan jumlah benda', 'Mencocokan Gambar', 2, @learning_module_academic_id);

-- Insert pertanyaan pertama (Jumlah Bebek)
SET @question_counting1_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting1_id, @topic_counting_id, 'image', '/uploads/images/bebek.png', 'Jumlah bebek ini adalah?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Jumlah Bebek"
SET @option1_qc1 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc1, @question_counting1_id, 'SATU');

SET @option2_qc1 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc1, @question_counting1_id, 'DUA');

SET @option3_qc1 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc1, @question_counting1_id, 'TIGA');

SET @option4_qc1 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc1, @question_counting1_id, 'EMPAT');

-- Insert jawaban benar untuk pertanyaan "Jumlah Bebek"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting1_id, @option1_qc1);

-- Insert pertanyaan kedua (Jumlah Bola)
SET @question_counting2_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting2_id, @topic_counting_id, 'image', '/uploads/images/dua_bola.png', 'Jumlah bola ini adalah?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Jumlah Bola"
SET @option1_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc2, @question_counting2_id, 'SATU');

SET @option2_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc2, @question_counting2_id, 'DUA');

SET @option3_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc2, @question_counting2_id, 'TIGA');

SET @option4_qc2 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc2, @question_counting2_id, 'EMPAT');

-- Insert jawaban benar untuk pertanyaan "Jumlah Bola"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting2_id, @option2_qc2);

-- Insert pertanyaan ketiga (Jumlah Apel)
SET @question_counting3_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting3_id, @topic_counting_id, 'image', '/uploads/images/tiga_apel.png', 'Jumlah apel ini adalah?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Jumlah Apel"
SET @option1_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc3, @question_counting3_id, 'DUA');

SET @option2_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc3, @question_counting3_id, 'TIGA');

SET @option3_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc3, @question_counting3_id, 'EMPAT');

SET @option4_qc3 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc3, @question_counting3_id, 'LIMA');

-- Insert jawaban benar untuk pertanyaan "Jumlah Apel"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting3_id, @option2_qc3);

-- Insert pertanyaan keempat (Jumlah Kucing)
SET @question_counting4_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting4_id, @topic_counting_id, 'image', '/uploads/images/empat_kucing.png', 'Jumlah kucing ini adalah?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Jumlah Kucing"
SET @option1_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc4, @question_counting4_id, 'TIGA');

SET @option2_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc4, @question_counting4_id, 'EMPAT');

SET @option3_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc4, @question_counting4_id, 'LIMA');

SET @option4_qc4 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc4, @question_counting4_id, 'ENAM');

-- Insert jawaban benar untuk pertanyaan "Jumlah Kucing"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting4_id, @option2_qc4);

-- Insert pertanyaan kelima (Jumlah Pensil)
SET @question_counting5_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting5_id, @topic_counting_id, 'image', '/uploads/images/lima_pensil.png', 'Jumlah pensil ini adalah?', FALSE, 0);

-- Insert opsi jawaban untuk pertanyaan "Jumlah Pensil"
SET @option1_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc5, @question_counting5_id, 'EMPAT');

SET @option2_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc5, @question_counting5_id, 'LIMA');

SET @option3_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc5, @question_counting5_id, 'ENAM');

SET @option4_qc5 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc5, @question_counting5_id, 'TUJUH');

-- Insert jawaban benar untuk pertanyaan "Jumlah Pensil"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting5_id, @option2_qc5);

-- Insert pertanyaan keenam (Pilih jumlah ikan)
SET @question_counting6_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting6_id, @topic_counting_id, 'image', '/uploads/images/tiga_ikan.png', 'Pilih nama yang sesuai untuk benda ini.', FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Pilih jumlah ikan"
SET @option1_qc6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc6, @question_counting6_id, 'SATU');

SET @option2_qc6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc6, @question_counting6_id, 'DUA');

SET @option3_qc6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc6, @question_counting6_id, 'TIGA');

SET @option4_qc6 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc6, @question_counting6_id, 'EMPAT');

-- Insert jawaban benar untuk pertanyaan "Pilih jumlah ikan"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting6_id, @option3_qc6);

-- Insert pertanyaan ketujuh (Jumlah Coklat - 4)
SET @question_counting7_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting7_id, @topic_counting_id, 'image', '/uploads/images/empat_coklat.png', 'Pilih nama yang sesuai untuk benda ini.', FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Jumlah Coklat - 4"
SET @option1_qc7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc7, @question_counting7_id, 'TIGA');

SET @option2_qc7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc7, @question_counting7_id, 'EMPAT');

SET @option3_qc7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc7, @question_counting7_id, 'LIMA');

SET @option4_qc7 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc7, @question_counting7_id, 'ENAM');

-- Insert jawaban benar untuk pertanyaan "Jumlah Coklat - 4"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting7_id, @option2_qc7);

-- Insert pertanyaan kedelapan (Jumlah Kue - 3)
SET @question_counting8_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting8_id, @topic_counting_id, 'image', '/uploads/images/tiga_kue.png', 'Pilih nama yang sesuai untuk benda ini.', FALSE, 1);

-- Insert opsi jawaban untuk pertanyaan "Jumlah Kue - 3"
SET @option1_qc8 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc8, @question_counting8_id, 'DUA');

SET @option2_qc8 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc8, @question_counting8_id, 'TIGA');

SET @option3_qc8 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc8, @question_counting8_id, 'EMPAT');

SET @option4_qc8 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc8, @question_counting8_id, 'LIMA');

-- Insert jawaban benar untuk pertanyaan "Jumlah Kue - 3"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting8_id, @option2_qc8);

-- Insert pertanyaan kesembilan (Multiple Choice - 4 Coklat)
SET @question_counting9_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting9_id, @topic_counting_id, 'image', '/uploads/images/empat_coklat.png', 'Pilih semua jawaban yang sesuai untuk benda ini.', TRUE, 1);

-- Insert opsi jawaban untuk pertanyaan "Multiple Choice - 4 Coklat"
SET @option1_qc9 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc9, @question_counting9_id, 'SATU');

SET @option2_qc9 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc9, @question_counting9_id, 'DUA');

SET @option3_qc9 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc9, @question_counting9_id, 'EMPAT');

SET @option4_qc9 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc9, @question_counting9_id, 'LIMA');

-- Insert jawaban benar untuk pertanyaan "Multiple Choice - 4 Coklat"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting9_id, @option3_qc9);

-- Insert pertanyaan kesepuluh (Multiple Choice - 4 Coklat & 3 Kue)
SET @question_counting10_id = uuid_to_bin(uuid());
INSERT INTO question (id, topic_id, media_type, src, text, is_multiple_option, level)
VALUES (@question_counting10_id, @topic_counting_id, 'image', '/uploads/images/empat_coklat_3_kue.png', 'Pilih semua jawaban yang sesuai untuk benda ini.', TRUE, 1);

-- Insert opsi jawaban untuk pertanyaan "Multiple Choice - 4 Coklat & 3 Kue"
SET @option1_qc10 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option1_qc10, @question_counting10_id, 'DUA');

SET @option2_qc10 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option2_qc10, @question_counting10_id, 'EMPAT');

SET @option3_qc10 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option3_qc10, @question_counting10_id, 'TIGA');

SET @option4_qc10 = uuid_to_bin(uuid());
INSERT INTO options (id, question_id, text) VALUES (@option4_qc10, @question_counting10_id, 'LIMA');

-- Insert jawaban benar untuk pertanyaan "Multiple Choice - 4 Coklat & 3 Kue"
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting10_id, @option2_qc10);
INSERT INTO answer (id, question_id, option_id) VALUES (uuid_to_bin(uuid()), @question_counting10_id, @option3_qc10);