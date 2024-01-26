use std::env;

fn main() {
    let args: Vec<String> = env::args().collect();

    if args.len() < 2 {
        eprintln!("Usage: {} PLATES DETERGENT", args[0]);

        return;
    }

    let plates: i32 = args[1].parse::<i32>().unwrap();
    let mut detergent: f32 = args[2].parse::<f32>().unwrap();

    for _ in 0..plates {
        let sub = 0.5;
        if (detergent - sub).is_sign_negative() {
            return;
        }
        detergent -= sub;
        println!("Detergent left: {}", detergent);
    }
}
