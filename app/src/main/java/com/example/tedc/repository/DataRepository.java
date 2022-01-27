package com.example.tedc.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;


import com.example.tedc.models.Complaint;
import com.example.tedc.models.LogBook;
import com.example.tedc.models.Post;
import com.example.tedc.models.Record;
import com.example.tedc.models.Skill;
import com.example.tedc.models.VerificationResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;


import java.util.ArrayList;

public class DataRepository {

    private static final String TAG = "DataRepository";

    FirebaseAuth auth;
    FirebaseFirestore db;
    FirebaseUser _user;
    User userO;
    String error;

    public DataRepository() {


        this.auth = FirebaseAuth.getInstance();
        this.db = FirebaseFirestore.getInstance();
        _user = auth.getCurrentUser();
    }


    //    ----------------- Saving Record ----------------
    public void saveRecord(Record record, MutableLiveData<VerificationResult> saveRes) {
        DocumentReference re = db.collection("record").document();
        record.setRecordId(re.getId());
        re.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                saveRes.postValue(new VerificationResult(false, record));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                saveRes.postValue(new VerificationResult(true, record));
                Log.i(TAG, "onFailure: error" + e.getMessage());
            }
        });
    }

    public void getAllRecord(MutableLiveData<VerificationResult> postResponse) {
        db.collection("record").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<Record> complaintArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Record record = documentSnapshot.toObject(Record.class);

                        complaintArrayList.add(record);


                    }
                    postResponse.postValue(new VerificationResult(false, complaintArrayList));
                } else {
                    postResponse.postValue(new VerificationResult(true, e.getMessage()));

                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


            }
        });
    }


//   ------------------------------- Post-----------------------------------

    public void getUserPosts(MutableLiveData<VerificationResult> postResponse) {
        db.collection("posts").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<Post> complaintArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Post post = documentSnapshot.toObject(Post.class);
                        if (post.getPosertId().equals(_user.getUid())) {
                            complaintArrayList.add(post);
                        }


                    }
                    postResponse.postValue(new VerificationResult(false, complaintArrayList));
                } else {
                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


            }
        });
    }

    public void getAllPosts(MutableLiveData<VerificationResult> postResponse) {
        db.collection("post").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<Post> complaintArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Post post = documentSnapshot.toObject(Post.class);

                        complaintArrayList.add(post);


                    }
                    postResponse.postValue(new VerificationResult(false, complaintArrayList));
                } else {
                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


            }
        });
    }


    public void sendPost(Post post, MutableLiveData<VerificationResult> saveRes) {
        DocumentReference re = db.collection("post").document();
        post.setPostId(re.getId());
        post.setPosertId(_user.getUid());
        re.set(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                saveRes.postValue(new VerificationResult(false, post));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                saveRes.postValue(new VerificationResult(true, e));
                Log.i(TAG, "onFailure: error" + e.getMessage());
            }
        });
    }



//    ------------------------- LogBook -----------------------



    public void getAllLogBooks(MutableLiveData<VerificationResult> postResponse) {
        db.collection("logBook").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<LogBook> logBookArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        LogBook logBook = documentSnapshot.toObject(LogBook.class);

                        logBookArrayList.add(logBook);


                    }
                    postResponse.postValue(new VerificationResult(false, logBookArrayList));
                } else {
                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


            }
        });
    }

    public void saveLogBook(LogBook logBook, MutableLiveData<VerificationResult> saveRes) {
        DocumentReference re = db.collection("logbook").document();
        logBook.setId(re.getId());
        re.set(logBook).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                saveRes.postValue(new VerificationResult(false, logBook));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                saveRes.postValue(new VerificationResult(true, e));
                Log.i(TAG, "onFailure: error" + e.getMessage());
            }
        });
    }














    //    -----------------------------Complaints ------------------------
    public void getUserComplaints(MutableLiveData<VerificationResult> orderResponse) {
        db.collection("complaint").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<Complaint> complaintArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Complaint complaint = documentSnapshot.toObject(Complaint.class);
                        assert complaint != null;
                        if (complaint.getSenderId().equals(_user.getUid())) {
                            complaintArrayList.add(complaint);


                        }

                    }
                    orderResponse.postValue(new VerificationResult(false, complaintArrayList));
                } else {
                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


//                    Log.i(TAG, "onEvent: " + documentSnapshot.getReference().toString());
//                    db.runTransaction(new Transaction.Function<Void>() {
//                        @Override
//                        public Void apply(Transaction transaction) throws FirebaseFirestoreException {
//                            DocumentSnapshot snapshot = transaction.get(documentSnapshot.getReference());
//
//                            // Note: this could be done without a transaction
//                            //       by updating the population using FieldValue.increment()
//                            snapshot.getReference().collection("order").get()
//                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                        @Override
//                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
//                                                Log.i(TAG, "onSuccess: " + doc.toObject(Order.class).getId());
//                                            }
//                                        }
//                                    });
//
//
//                            // Success
//                            return null;
//                        }
//                    });

            }
        });
    }

    public void getAllComplaints(MutableLiveData<VerificationResult> orderResponse) {
        db.collection("complaint").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<Complaint> complaintArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Complaint complaint = documentSnapshot.toObject(Complaint.class);
                        assert complaint != null;

                        complaintArrayList.add(complaint);


                    }
                    orderResponse.postValue(new VerificationResult(false, complaintArrayList));
                } else {
                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


            }
        });
    }


    public void sendComplaint(Complaint complaint, MutableLiveData<VerificationResult> saveRes) {
        DocumentReference re = db.collection("complaint").document();
        complaint.setComplaintId(re.getId());
        complaint.setSenderId(_user.getUid());
        re.set(complaint).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                saveRes.postValue(new VerificationResult(false, complaint));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                saveRes.postValue(new VerificationResult(true, complaint));
                Log.i(TAG, "onFailure: error" + e.getMessage());
            }
        });
    }


//    --------------- Skills --------------------------------

    public void getUserSkills(MutableLiveData<VerificationResult> orderResponse) {
        db.collection("complaint").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<Complaint> complaintArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Complaint complaint = documentSnapshot.toObject(Complaint.class);
                        assert complaint != null;
                        if (complaint.getSenderId().equals(_user.getUid())) {
                            complaintArrayList.add(complaint);


                        }

                    }
                    orderResponse.postValue(new VerificationResult(false, complaintArrayList));
                } else {
                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


//                    Log.i(TAG, "onEvent: " + documentSnapshot.getReference().toString());
//                    db.runTransaction(new Transaction.Function<Void>() {
//                        @Override
//                        public Void apply(Transaction transaction) throws FirebaseFirestoreException {
//                            DocumentSnapshot snapshot = transaction.get(documentSnapshot.getReference());
//
//                            // Note: this could be done without a transaction
//                            //       by updating the population using FieldValue.increment()
//                            snapshot.getReference().collection("order").get()
//                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                        @Override
//                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
//                                                Log.i(TAG, "onSuccess: " + doc.toObject(Order.class).getId());
//                                            }
//                                        }
//                                    });
//
//
//                            // Success
//                            return null;
//                        }
//                    });

            }
        });
    }

    public void getAllSkills(MutableLiveData<VerificationResult> orderResponse) {
        db.collection("complaint").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e == null) {
                    ArrayList<Complaint> complaintArrayList = new ArrayList<>();
                    Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Complaint complaint = documentSnapshot.toObject(Complaint.class);
                        assert complaint != null;

                        complaintArrayList.add(complaint);


                    }
                    orderResponse.postValue(new VerificationResult(false, complaintArrayList));
                } else {
                    Log.i(TAG, "onEvent: " + e.getMessage());
                }


            }
        });
    }

    public void saveSkills(Skill skill, MutableLiveData<VerificationResult> saveRes) {
        DocumentReference re = db.collection("skills").document();
        skill.setSkillId(re.getId());
        re.set(skill).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                saveRes.postValue(new VerificationResult(false, skill));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                saveRes.postValue(new VerificationResult(true, skill));
                Log.i(TAG, "onFailure: error" + e.getMessage());
            }
        });
    }










//
//    //    ----------------- getting User Details --------------
//    public void getUser(MutableLiveData<VerificationResult> response) {
//
//        db.collection("users").document(_user.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//
//                if (e == null) {
//                    response.postValue(new VerificationResult(false, documentSnapshot.toObject(User.class)));
////                    userO = documentSnapshot.toObject(User.class);
////                    user.postValue(userO);
////                    Log.i(TAG, "onEvent: " + userO.getName());
//                } else {
//                    response.postValue(new VerificationResult(true, e.getMessage()));
//                }
//
//
//            }
//        });
//
//    }
//
//
////    -------------------- Updating profile picture -------------
//
//    public void uploadPicture(String uri, MutableLiveData<VerificationResult> verificationResultMutableLiveData) {
//        db.collection("users").document(_user.getUid()).update("pictureUrl", uri).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                verificationResultMutableLiveData.postValue(new VerificationResult(false, _user));
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                verificationResultMutableLiveData.postValue(new VerificationResult(true, e.getMessage()));
//
//            }
//        });
//    }
//
////               ------------- getting Recent Trips -------------
//
//    public void getRecent(MutableLiveData<VerificationResult> recentResponse) {
//
//        db.collection("orders").limit(5).addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if (e == null) {
//                    ArrayList<Order> orderArrayList = new ArrayList<>();
//                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                        Order order = documentSnapshot.toObject(Order.class);
//                        if (order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                            orderArrayList.add(order);
//
//
//                        }
//                        recentResponse.postValue(new VerificationResult(false, orderArrayList));
//                    }
//                } else {
//                    recentResponse.postValue(new VerificationResult(true, e.getMessage()));
//                }
//            }
//        });
//    }
//
//    public void getRect(MutableLiveData<VerificationResult> verificationResult) {
//        db.collection("orders").document(_user.getUid()).collection("order").limit(5)
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                        if (e == null) {
//
//                            ArrayList<Order> orderArrayList = new ArrayList<>();
//                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
//
//                                orderArrayList.add(documentSnapshot.toObject(Order.class));
//                                verificationResult.postValue(new VerificationResult(false, orderArrayList));
//
//
//                            }
//                        } else {
//                            verificationResult.postValue(new VerificationResult(true, e.getMessage()));
//                            Log.i(TAG, "onEvent: " + e.getMessage());
//                        }
//                    }
//                });
//    }
//
//    //                ------------ Active Trips ------------
//    public void getActiveTrips(MutableLiveData<VerificationResult> verificationResult) {
//        db.collection("orders")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//
//                        if (e == null) {
//
//                            ArrayList<Order> activeArrayList = new ArrayList<>();
//
//                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
//                                Order order = documentSnapshot.toObject(Order.class);
//                                if (order.getOrderDetails().getStatus().equals("active") && order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                                    activeArrayList.add(order);
//                                }
//
//
//                            }
//                            verificationResult.postValue(new VerificationResult(false, activeArrayList));
//                        } else {
//                            verificationResult.postValue(new VerificationResult(true, e.getMessage()));
//                            Log.i(TAG, "onEvent: " + e.getMessage());
//                        }
//
//                    }
//                });
//    }
//
//
//
//    //              ----------- getting Pending Trips ------------
//    public void getPendingTrips(MutableLiveData<VerificationResult> verificationResult) {
//        db.collection("orders")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                        if (e == null) {
//
//                            ArrayList<Order> pendingArrayList = new ArrayList<>();
//                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
//                                Order order = documentSnapshot.toObject(Order.class);
//                                if (order.getOrderDetails().getStatus().equals("pending") && order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                                    pendingArrayList.add(order);
//                                }
//
//                            }
//                            verificationResult.postValue(new VerificationResult(false, pendingArrayList));
//
//
//                        } else {
//                            verificationResult.postValue(new VerificationResult(true, e.getMessage()));
//                            Log.i(TAG, "onEvent: " + e.getMessage());
//                        }
//
//                    }
//                });
//    }
//
//    public void getDeliveredTrips(MutableLiveData<VerificationResult> verificationResult) {
//        db.collection("orders")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//
//                        if (e == null) {
//
//                            ArrayList<Order> deliveredArraylist = new ArrayList<>();
//                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
//                                Order order = documentSnapshot.toObject(Order.class);
//                                if (order.getOrderDetails().getStatus().equals("delivered") && order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                                    deliveredArraylist.add(order);
//                                }
//
//                            }
//                            verificationResult.postValue(new VerificationResult(false, deliveredArraylist));
//                        } else {
//                            verificationResult.postValue(new VerificationResult(true, e.getMessage()));
//                            Log.i(TAG, "onEvent: " + e.getMessage());
//                        }
//                    }
//                });
//
//
//    }
//
//    //        --------- Track Order --------
//
//    public void trackOrder(MutableLiveData<VerificationResult> tracResponse, String id) {
//        db.collection("orders").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                if (!queryDocumentSnapshots.isEmpty()) {
//                    if (e == null) {
//                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                            Order order = documentSnapshot.toObject(Order.class);
//
//                            if (order.getId().equals(id)) {
//
//                                tracResponse.postValue(new VerificationResult(false, order));
//                            } else {
//                                tracResponse.postValue(new VerificationResult(true, "Invalid Tracking Id"));
//                            }
//                        }
//                    } else {
//                        tracResponse.postValue(new VerificationResult(true, e.getMessage()));
//                    }
//                } else {
//                    tracResponse.postValue(new VerificationResult(true, "You have no open Orders"));
//                }
//            }
//        });
//
//    }
//
////                    ------------ Search by Id -----------
//
//
//    public void searchOrder(MutableLiveData<VerificationResult> searchResponse, String id) {
//
//        db.collection("orders")
//                .document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//            @Override
//            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                Order order = documentSnapshot.toObject(Order.class);
//                if (order != null) {
//                    Log.i(TAG, "onSuccess: " + order.getId());
//                    searchResponse.postValue(new VerificationResult(false, order));
//                } else {
//                    searchResponse.postValue(new VerificationResult(true, "Not found"));
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                searchResponse.postValue(new VerificationResult(true, e.getMessage()));
//            }
//        });
//    }
//
//
//    //                  ---------- Recycle Order ---------
//    public void recycleOrder(Order order, MutableLiveData<VerificationResult> saveRes) {
//
//        DocumentReference re = db.collection("recycleBin")
//                .document(order.getId());
//        order.setId(order.getId());
//        re.set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//
//                saveRes.postValue(new VerificationResult(false, order.getId()));
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                saveRes.postValue(new VerificationResult(true, order));
//                Log.i(TAG, "onFailure: error" + e.getMessage());
//            }
//        });
//    }
//
//    //                  ---------- Re-Order ---------
//    public void reOrder(Order order, MutableLiveData<VerificationResult> saveRes) {
//
//        DocumentReference re = db.collection("orders")
//                .document(order.getId());
//        order.setId(order.getId());
//        re.set(order).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//
//                saveRes.postValue(new VerificationResult(false, order.getId()));
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                saveRes.postValue(new VerificationResult(true, order));
//                Log.i(TAG, "onFailure: error" + e.getMessage());
//            }
//        });
//    }
//
//    //                  ------------- Delete Order ---------
//    public void deleteOrder(String id, MutableLiveData<VerificationResult> deleteResponse) {
//        db.collection("orders").document(id)
//                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.i(TAG, "onSuccess: succesful");
//                deleteResponse.postValue(new VerificationResult(false, id));
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                deleteResponse.postValue(new VerificationResult(true, e.getMessage()));
//            }
//        });
//
//    }
//
//    //                  ------------- Delete From Recyclebin ---------
//    public void clearFromRecycleBin(String id, MutableLiveData<VerificationResult> deleteResponse) {
//        db.collection("recycleBin").document(id)
//                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.i(TAG, "onSuccess: succesful");
//                deleteResponse.postValue(new VerificationResult(false, id));
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                deleteResponse.postValue(new VerificationResult(true, e.getMessage()));
//            }
//        });
//
//    }
////              ------------ getting Canceled Orders ---------
//
//    public void getCanceledOrders(MutableLiveData<VerificationResult> cancelResponse) {
//
//        db.collection("recycleBin")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                        if (e == null) {
//
//                            ArrayList<Order> canceledOrders = new ArrayList<>();
//                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//
//
//                                Order order = documentSnapshot.toObject(Order.class);
//                                if (order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                                    canceledOrders.add(order);
//                                }
//                                cancelResponse.postValue(new VerificationResult(false, canceledOrders));
//
//                            }
//                        } else {
//                            cancelResponse.postValue(new VerificationResult(true, e.getMessage()));
//                        }
//                    }
//                });
//
//
//    }
//
//
//    public void getOrders() {
//        db.collection("orders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//
//                    Log.i(TAG, "onComplete: " + task.getResult());
//                }
//            }
//        });
//    }
//

//
//    public void getAlOrders() {
//        Order order = new Order();
//        db.collection("orders").whereEqualTo("id", "delivered").addSnapshotListener(new EventListener<QuerySnapshot>() {
//
//
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                Log.i(TAG, "onDvent: " + queryDocumentSnapshots.size());
//
//            }
//        });
//    }
//
//    //              ------------- sortActiveByDate ------------
//    public void sortActiveByDate(long date, MutableLiveData<VerificationResult> sortActiveResponse) {
//        db.collection("orders").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                if (e == null) {
//                    ArrayList<Order> orderArrayList = new ArrayList<>();
//                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                        Order order = documentSnapshot.toObject(Order.class);
//                        if (order.getOrderDetails().getDate() == date && order.getOrderDetails()
//                                .getStatus().equals("active") && order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                            orderArrayList.add(order);
//                        }
//
//                    }
//                    sortActiveResponse.postValue(new VerificationResult(false, orderArrayList));
//                } else {
//                    sortActiveResponse.postValue(new VerificationResult(false, e.getMessage()));
//                }
//            }
//        });
//    }
//
//    //              ------------- sortPendingByDate ------------
//    public void sortPendingByDate(long date, MutableLiveData<VerificationResult> sortPendingResponse) {
//        db.collection("orders").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                if (e == null) {
//                    ArrayList<Order> orderArrayList = new ArrayList<>();
//                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                        Order order = documentSnapshot.toObject(Order.class);
//                        if (order.getOrderDetails().getDate() == date && order.getOrderDetails()
//                                .getStatus().equals("pending") && order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                            orderArrayList.add(order);
//
//                        }
//
//                    }
//                    sortPendingResponse.postValue(new VerificationResult(false, orderArrayList));
//                } else {
//                    sortPendingResponse.postValue(new VerificationResult(true, e.getMessage()));
//                }
//            }
//        });
//    }
//
//    //                  --------- getting Orders by date --------------
//    public void SortByDate(long date, MutableLiveData<VerificationResult> sortResponse) {
//        db.collection("orders").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                Log.i(TAG, "onEvent: " + queryDocumentSnapshots.size());
//                ArrayList<Order> orderArrayList = new ArrayList<>();
//                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                    Order order = documentSnapshot.toObject(Order.class);
//                    if (order.getOrderDetails().getDate() == date) {
//                        orderArrayList.add(order);
//
//                    }
//                    sortResponse.postValue(new VerificationResult(false, orderArrayList));
//                }
//
//            }
//        });
//
//
//    }
//
//    //              ------------- sortCanceledByDate ------------
//    public void sortCanceledByDate(long date, MutableLiveData<VerificationResult> sortCanceledRequest) {
//        db.collection("recycle").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                if (e == null) {
//                    ArrayList<Order> orderArrayList = new ArrayList<>();
//                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                        Order order = documentSnapshot.toObject(Order.class);
//                        if (order.getOrderDetails().getDate() == date && order.getOrderDetails()
//                                .getOrederdBy().equals(_user.getUid())) {
//                            orderArrayList.add(order);
//                        }
//
//                    }
//
//                    sortCanceledRequest.postValue(new VerificationResult(false, orderArrayList));
//                } else {
//                    sortCanceledRequest.postValue(new VerificationResult(true, e.getMessage()));
//                }
//            }
//        });
//    }
//
//
//    //              ------------- sortDeliveredByDate ------------
//    public void sortDeliveredByDate(long date, MutableLiveData<VerificationResult> sortPendingResponse) {
//        db.collection("orders").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                if (e == null) {
//                    ArrayList<Order> orderArrayList = new ArrayList<>();
//                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
//                        Order order = documentSnapshot.toObject(Order.class);
//                        if (order.getOrderDetails().getDate() == date && order.getOrderDetails()
//                                .getStatus().equals("delivered") && order.getOrderDetails().getOrederdBy().equals(_user.getUid())) {
//                            orderArrayList.add(order);
//                        }
//
//                    }
//                } else {
//                    sortPendingResponse.postValue(new VerificationResult(false, e.getMessage()));
//                }
//            }
//        });
//    }
//
//    public void recycler(Order order, MutableLiveData<VerificationResult> saveRes) {
//        WriteBatch batch = db.batch();
//        DocumentReference re = db.collection("recycleBin")
//                .document(order.getId());
//        order.setId(order.getId());
////        order.getOrderDetails().setDate(new Date().getDate());
//        batch.set(re, order);
//        DocumentReference delre = db.collection("orders").document(order.getId());
//        batch.delete(delre);
//        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    saveRes.postValue(new VerificationResult(false, "Deleted"));
//                } else {
//                    saveRes.postValue(new VerificationResult(true, task.getException().getMessage()));
//                }
//            }
//        });
//
//
//    }
//
//    public void ReOrder(Order order, MutableLiveData<VerificationResult> saveRes) {
//        WriteBatch batch = db.batch();
//        DocumentReference re = db.collection("orders")
//                .document(order.getId());
//        order.setId(order.getId());
////        order.getOrderDetails().setDate(new Date().getDate());
//        batch.set(re, order);
//        DocumentReference delre = db.collection("recycleBin").document(order.getId());
//        batch.delete(delre);
//        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    saveRes.postValue(new VerificationResult(false, "Restored"));
//                } else {
//                    saveRes.postValue(new VerificationResult(true, task.getException().getMessage()));
//                }
//            }
//        });
//
//    }
//
    //                   -------------Saving Errands--------------
//    public void SendErrand(Item item, MutableLiveData<VerificationResult> response) {
//        DocumentReference documentReference = db.collection("Errands").document();
//        item.setId(documentReference.getId());
//        item.setOrderedBy(_user.getUid());
//        item.setDate(new Date().getTime());
//        documentReference.set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                response.postValue(new VerificationResult(false, item));
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                response.postValue(new VerificationResult(true, e.getMessage()));
//            }
//        });
//
//    }
//
//    public void SaveItem(Uri uri, Item item, MutableLiveData<VerificationResult> response) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference reference = storage.getReference();
//        StorageReference storageReference = reference.child("images/" + uri.getLastPathSegment());
//        UploadTask uploadTask = storageReference.putFile(uri);
//
//        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//            }
//        });
//
//        Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//            @Override
//            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                return storageReference.getDownloadUrl();
//            }
//        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//            @Override
//            public void onComplete(@NonNull Task<Uri> task) {
//                if (task.isSuccessful()) {
//                    item.setImageUri(Objects.requireNonNull(task.getResult()).toString());
//                    SendErrand(item, response);
//                } else {
//                    response.postValue(new VerificationResult(true, task.getException().getMessage()));
//                }
//            }
//        });
//
//    }
//
//    //      --------- get Items  --------
//    public void getItems(MutableLiveData<VerificationResult> response) {
//
//        db.collection("Errands").whereEqualTo("orderedBy", _user.getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//
//                if (e == null) {
//                    ArrayList<Item> itemArrayList = new ArrayList<>();
//                    for (DocumentSnapshot reference : queryDocumentSnapshots.getDocuments()) {
//                        Item item = reference.toObject(Item.class);
//                        itemArrayList.add(item);
//                    }
//                    response.postValue(new VerificationResult(false, itemArrayList));
//                } else {
//
//                    response.postValue(new VerificationResult(true, e.getMessage()));
//                }
//
//            }
//        });
//    }
}