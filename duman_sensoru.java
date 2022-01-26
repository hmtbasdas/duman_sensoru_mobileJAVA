public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    TextView valueText;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("gazDedektor");

        valueText = findViewById(R.id.value);
        resultText = findViewById(R.id.result);

        getDetectorValueListener();
    }

    private void getDetectorValueListener() {
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                valueText.setText(String.valueOf(snapshot.child("durum").getValue()));
                if(Integer.parseInt(String.valueOf(snapshot.child("durum").getValue())) > 600){
                    resultText.setText("Gaz yoğunluğu algılandı");
                    resultText.setTextColor(getResources().getColor(R.color.text2));
                }
                else {
                    resultText.setText("Her şey normal");
                    resultText.setTextColor(getResources().getColor(R.color.text1));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}