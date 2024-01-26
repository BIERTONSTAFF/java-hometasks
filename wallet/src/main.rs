use std::io;

use models::currency::Currency;

mod models;

fn main() {
    let mut currencies: Vec<Currency> = vec![];

    Currency::init(&mut currencies);

    let mut buffer = String::new();
    if let Ok(_) = io::stdin().read_line(&mut buffer) {
        if let Ok(amount) = buffer.trim().parse::<f32>().clone() {
            buffer.clear();

            if let Ok(_) = io::stdin().read_line(&mut buffer) {
                if let Some(currency) = currencies.iter().find(|c| {
                    c.code == {
                        let this = &buffer.trim();
                        *this
                    }
                }) {
                    println!(
                        "You've select {}, your wallet balance is {}{}",
                        currency.name,
                        currency.calc(amount),
                        currency.code
                    )
                } else {
                    eprintln!("No currency found");
                }
            }
        }
    }
}
