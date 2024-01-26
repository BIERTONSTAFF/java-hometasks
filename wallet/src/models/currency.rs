use serde::{Deserialize, Serialize};

#[derive(Serialize, Deserialize)]
pub struct Currency {
    pub name: String,
    pub code: String,
    pub cost: f32,
}

impl Currency {
    pub fn new(name: String, code: String, cost: f32) -> Self {
        Self { name, code, cost }
    }
    pub fn init(currencies: &mut Vec<Self>) {
        currencies.push(Currency::new(
            "Swedish Krona".to_string(),
            "SEK".to_string(),
            10.41,
        ));
        currencies.push(Currency::new("Euro".to_string(), "EUR".to_string(), 1.09));
        currencies.push(Currency::new(
            "US Dollar".to_string(),
            "USD".to_string(),
            1.0,
        ));
        currencies.push(Currency::new(
            "Japanese Yen".to_string(),
            "JPY".to_string(),
            147.72,
        ));
        currencies.push(Currency::new(
            "Russian Ruble".to_string(),
            "RUB".to_string(),
            80.50,
        ));
    }
    pub fn calc(&self, amount: f32) -> f32 {
        amount * self.cost
    }
}
